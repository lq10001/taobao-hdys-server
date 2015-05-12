/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ly.util;

import com.ly.comm.AppContext;
import com.ly.taobao.service.ProductService;
import com.ly.taobao.service.ProductimgService;
import com.ly.taobao.vo.Product;
import com.ly.taobao.vo.Productimg;
import net.sf.ehcache.CacheManager;
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

        ProductService productSrv = AppContext.getBean(ProductService.class);
        ProductimgService productImgSrv = AppContext.getBean(ProductimgService.class);


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
                        product.setPicurl(img_url.substring(0, len));

                        //title
                        Element pTitle = li.select("p[class=tit]").first();
                        product.setName(pTitle.text());

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
                        product.setOrdernum(order);

                        Product old_product = productSrv.fetch(Cnd.where("num_iid", "=", num_iid));
                        //
                        if (old_product == null) {
                            Product newProduct = productSrv.dao().insert(product);
                            doc2 = Jsoup.connect(product.getUrl()).data("query", "java").userAgent("Mozilla").timeout(30000).cookie("auth", "token").post();

                            Elements imgs = doc2.select("table[class=mt] img");
                            if (!imgs.isEmpty()) {
                                for (Element img2 : imgs) {
                                    Productimg pi = new Productimg();
                                    pi.setProductid(newProduct.getId());

                                    String img_url2 = img2.attr("src");
                                    int len2 = img_url2.lastIndexOf("_70x70.jpg");
                                    pi.setUrl(img_url2.substring(0, len2));

                                    productImgSrv.dao().insert(pi);
                                }
                            }

                        } else {

                            productSrv.dao().update(product);
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

                        Product old_product = productSrv.fetch(Cnd.where("num_iid", "=", num_iid));

                        if (old_product != null) {
                            long order = i * 20 + j;
                            Product newProduct = new Product();
                            newProduct.setId(old_product.getId());
                            newProduct.setOrdernum(order);
                            productSrv.dao().update(newProduct);
                        }
                        j++;
                    }
                }
            }
            
        System.out.println("========================================");
        
        CacheManager.getInstance().removeCache("product");
        CacheManager.getInstance().removeCache("productimg");


        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        

    }
}
