/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ly.util;

import com.ly.comm.AppContext;
import com.ly.comm.CacheData;
import com.ly.comm.Page;
import com.ly.sys.srv.ProductImgSrv;
import com.ly.sys.srv.ProductSrv;
import com.ly.sys.vo.Product;
import com.ly.sys.vo.ProductImg;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.dao.Cnd;

/**
 *
 * @author zw
 */
public class LoadTaobaoData implements Runnable {

    static String new_url = "http://handuyishe.m.tmall.com/shop/shop_auction_search.htm?conditions=&sid=9ee8445d31cc7a85&sort=oldstarts&suid=263817957&q=&end_price=&pds=newrank%23h%23shop&ascid=&scid=&start_price=&p=";
    static String sale_url = "http://handuyishe.m.tmall.com/shop/shop_auction_search.htm?conditions=&sid=d01d10e83cd4a09d&sort=hotsell&suid=263817957&q=&end_price=&pds=salerank%23h%23shop&ascid=&scid=&start_price=&p=";
    static String url2 = "http://a.m.tmall.com/i3007043285.htm?sid=a0e41cd5c3efd8ec&pds=fromauc%23h%23shop";

    @Override
    public void run() {

        ProductSrv productSrv = AppContext.getBean(ProductSrv.class);
        ProductImgSrv productImgSrv = AppContext.getBean(ProductImgSrv.class);


        Document doc, doc2;
        try {
            
            for (int i = 0; i < 500; i++) {
                String str_url = new_url + i;
                doc = Jsoup.connect(str_url).data("query", "java").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22").timeout(30000).cookie("auth", "token").post();

                Elements divs = doc.select("div[class=block first]");
                if (divs.isEmpty()) {
                    continue;
                }

                for (Element div1 : divs) {

                    Product product = new Product();

                    Elements lis = div1.select("li>a");
                    if (lis.isEmpty()) {
                        continue;
                    }
                    int j = 1;
                    for (Element li : lis) {
                        product.setUrl(li.attr("href"));

                        //img new_url
                        Element img = li.select("img").first();
                        String img_url = img.attr("src");
                        int len = img_url.lastIndexOf("_145x145.jpg");
                        product.setPic_url(img_url.substring(0, len));

                        //title
                        Element pTitle = li.select("p[class=tit]").first();
                        product.setTitle(pTitle.text());

                        //price
                        Element price = li.select("p[class=price]").first();
                        String[] p1 = price.text().split("[ ]");
                        product.setPrice(p1[0]);

                        String[] arrSplit = product.getUrl().split("[?]");
                        String[] arrSplit2 = arrSplit[0].split("[/]");
                        String[] arrSplit3 = arrSplit2[3].split("[.]");
                        String num_iid = arrSplit3[0];
                        product.setNum_iid(num_iid);
                        
                        long order = i * 20 + j;
                        product.setOrderNum(order);


                        Product old_product = productSrv.queryObj(Cnd.where("num_iid", "=", num_iid));
                        //                    
                        if (old_product == null) {
                            
                            Product newProduct = productSrv.insert(product);
                            

                            doc2 = Jsoup.connect(product.getUrl()).data("query", "java").userAgent("Mozilla").timeout(30000).cookie("auth", "token").post();

                            Elements imgs = doc2.select("table[class=mt] img");
                            if (!imgs.isEmpty()) {
                                for (Element img2 : imgs) {
                                    ProductImg pi = new ProductImg();
                                    pi.setProductId(newProduct.getProductid());

                                    String img_url2 = img2.attr("src");
                                    int len2 = img_url2.lastIndexOf("_70x70.jpg");
                                    pi.setImgurl(img_url2.substring(0, len2));

                                    productImgSrv.insert(pi);
                                }
                            }

                        } else {
                            
                            productSrv.update(product);
                        }
                        j++;

                    }
                }
            }


            //sale
            for (int i = 0; i < 500; i++) {
                String str_url = sale_url + i;
                doc = Jsoup.connect(str_url).data("query", "java").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22").timeout(30000).cookie("auth", "token").post();

                Elements divs = doc.select("div[class=block first]");
                if (divs.isEmpty()) {
                    continue;
                }


                for (Element div1 : divs) {

                    Product product = new Product();

                    Elements lis = div1.select("li>a");
                    if (lis.isEmpty()) {
                        continue;
                    }
                    int j = 1;
                    for (Element li : lis) {


                        String[] arrSplit = li.attr("href").split("[?]");
                        String[] arrSplit2 = arrSplit[0].split("[/]");
                        String[] arrSplit3 = arrSplit2[3].split("[.]");
                        String num_iid = arrSplit3[0];
                        product.setNum_iid(num_iid);

                        Product old_product = productSrv.queryObj(Cnd.where("num_iid", "=", num_iid));

                        if (old_product != null) {
                            long order = i * 20 + j;
                            Product newProduct = new Product();
                            newProduct.setProductid(old_product.getProductid());
                            newProduct.setOrderNum(order);
                            productSrv.update(newProduct);
                        }
                        j++;
                    }
                }
            }


        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
        
        Page p = new Page();
        p.setNumPerPage(20);
        p.setPageNum(1);
        
        //new
        List<Product>  product_list =  productSrv.query(Cnd.wrap("productid >0  order by productid desc"), p);
        Map<Object, Object> newMapmap = new HashMap<Object, Object>();  
        newMapmap.put("product_list", product_list);
        CacheData.getInstance().setNewProductMap(newMapmap);

        
        //sale
        List<Product>  sale_product_list =  productSrv.query(Cnd.wrap("productid >0  order by ordernum"), p);
        Map<Object, Object> saleMapmap = new HashMap<Object, Object>();  
        saleMapmap.put("product_list", sale_product_list);
        CacheData.getInstance().setSaleProductMap(saleMapmap);

        
    }
}
