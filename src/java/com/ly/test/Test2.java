/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ly.test;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




/**
 *
 * @author zw
 */
public class Test2 {
    
    static String url = "http://handuyishe.m.tmall.com/shop/shop_auction_search.htm?conditions=&sid=9ee8445d31cc7a85&sort=oldstarts&suid=263817957&q=&end_price=&pds=newrank%23h%23shop&ascid=&scid=&start_price=&p=";
    static String url2 = "http://a.m.tmall.com/i3007043285.htm?sid=a0e41cd5c3efd8ec&pds=fromauc%23h%23shop";
    
    public static void  main(String[] args) throws  IOException
    {
        Document doc = Jsoup.connect(url2).data("query","java").userAgent("Mozilla").timeout(30000).cookie("auth", "token").post(); 
        System.out.println(doc.html());

        Elements divs = doc.select("table[class=mt] img");
        for (Element li : divs) {
            System.out.println(li.attr("src"));
        }


            
        }
//    }
    
}
