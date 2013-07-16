package com.ly.sys.act;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ly.comm.CommAction;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import com.ly.sys.srv.GlobalSrv;
import com.ly.sys.srv.ProductImgSrv;
import com.ly.sys.srv.ProductSrv;
import com.ly.sys.vo.Product;
import com.ly.sys.vo.ProductImg;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



@IocBean
@InjectName("productAct")
@At("/sys/product")
@Fail("json")
public class ProductAct extends CommAction{
	
        static String url = "http://handuyishe.m.tmall.com/shop/shop_auction_search.htm?conditions=&sid=9ee8445d31cc7a85&sort=oldstarts&suid=263817957&q=&end_price=&pds=newrank%23h%23shop&ascid=&scid=&start_price=&p=";
        static String url2 = "http://a.m.tmall.com/i3007043285.htm?sid=a0e41cd5c3efd8ec&pds=fromauc%23h%23shop";

    
	private static final Log log = Logs.getLog(ProductAct.class);

	@Inject
	private ProductSrv productSrv;
        
        @Inject
        private GlobalSrv globalSrv;
        
        @Inject
	private ProductImgSrv productImgSrv;
        
        
        @At
        @Ok("json")
        public Map<String, String> refresh(@Param("..") Page page, HttpServletRequest request)
        {
            int rtnNum = 0;            
            return super.tabMap((rtnNum > 0)?"200":"300", "product", "");
        }

	@At
	@Ok("jsp:/WEB-INF/page/sys/product_list.jsp")
	public void list(@Param("..") Page p, HttpServletRequest request,@Param("..") Product  product) {
		Cnd c = new ParseObj(product).getCnd();
		List<Product>  product_list =  productSrv.query(c, p);
		p.setRecordCount(productSrv.cnt(c));
		
		request.setAttribute("product",  product);
		request.setAttribute("product_list", product_list);
		request.setAttribute("page", p);
	}
        
        

	@At
	@Ok("jsp:/WEB-INF/page/sys/product.jsp")
	public void edit(@Param("productid")Long productid,HttpServletRequest request) {
		if(productid == null || productid == 0){
			request.setAttribute("product", null);
		}else{
			Product product  = productSrv.queryObj(productid);
			request.setAttribute("product", product);
		}
	}

	@At
	@Ok("json")
	public Map<String,String> save(@Param("..")Product product){
        Object rtnObj;
		if(product.getProductid() == null || product.getProductid() == 0){
			rtnObj = productSrv.insert(product);
		}else{
			rtnObj = productSrv.update(product);
		}
		return super.tabMap((rtnObj != null)?"200":"300", "product","closeCurrent");
	}
	
	@At
	@Ok("json")
	public Map<String, String> del(@Param("productId")Long productId){
		Object rtnObj =  productSrv.del(productId);
		return super.tabMap((rtnObj != null)?"200":"300", "product","");
	}
        
        @At
	@Ok("json")
	public Map<String, String> auto(){
            Document doc,doc2;
            String rtnStr = "300";
            try{
            for(int i = 0; i < 500;i++)
            {
                String str_url = url + i;
                doc = Jsoup.connect(str_url).data("query","java").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22").timeout(30000).cookie("auth", "token").post(); 

                Elements divs = doc.select("div[class=block first]");
                if(divs.isEmpty())continue;
                
                for (Element div1 : divs) {
                    
                    Product product = new Product();
                    
                    Elements lis = div1.select("li>a");
                    if(lis.isEmpty())continue;
                    for (Element li : lis) {
                        product.setUrl(li.attr("href"));
                        
                        //img url
                        Element img = li.select("img").first();
                        String img_url = img.attr("src");
                        int len = img_url.lastIndexOf("_145x145.jpg");
                        product.setPic_url(img_url.substring(0,len));

                        //title
                        Element pTitle = li.select("p[class=tit]").first();
                        product.setTitle(pTitle.text());

                        //price
                        Element price = li.select("p[class=price]").first();
                        String[] p1=price.text().split("[ ]");
                        product.setPrice(p1[0]);
                        
                        String[] arrSplit=product.getUrl().split("[?]"); 
                        String[] arrSplit2=arrSplit[0].split("[/]");
                        String[] arrSplit3=arrSplit2[3].split("[.]");
                        String num_iid = arrSplit3[0];
                        product.setNum_iid(num_iid); 

                        Product old_product = this.productSrv.queryObj(Cnd.where("num_iid","=",num_iid));

    //                    
                        if(old_product == null){
                            Product newProduct = productSrv.insert(product);
                             
                            doc2 = Jsoup.connect(product.getUrl()).data("query","java").userAgent("Mozilla").timeout(30000).cookie("auth", "token").post(); 

                            Elements imgs = doc2.select("table[class=mt] img");
                            if(!imgs.isEmpty()){
                                for (Element img2 : imgs) {
                                    ProductImg pi = new ProductImg();
                                    pi.setProductId(newProduct.getProductid());
                                    
                                    String img_url2 = img2.attr("src");
                                    int len2 = img_url2.lastIndexOf("_70x70.jpg");
                                    pi.setImgurl(img_url2.substring(0,len2));
                                    
                                    this.productImgSrv.insert(pi);
                                }
                            }
                             
                        }else{
                             productSrv.update(product);
                        }

                    }
                }
                rtnStr = "200";
            }
            
            }catch(Exception ex)
            {
                log.info(ex.toString());
                rtnStr = "300";
            }
            return super.tabMap(rtnStr, "product","");
	}
}