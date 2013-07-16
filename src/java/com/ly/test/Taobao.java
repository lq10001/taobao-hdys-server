/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ly.test;

import com.sun.org.apache.bcel.internal.generic.DLOAD;



/**
 *
 * @author zw
 */
public class Taobao {
    
    static String appkey = "1021502033";
    static String secret = "sandbox51fffbfceb76b5c29e4a02f2a"; 
    static String url = "http://gw.api.tbsandbox.com/router/rest";
    
    public static void  main(String[] args) 
    {
        
                        String str = "http://q.i04.wimg.taobao.com/bao/uploaded/i4/17957023974882191/T1GoG.jpg2XqRgXXXXXXXX_!!0-item_pic.jpg_145x145.jpg";
//                        String[] arrSplit=str.split(".jpg");
                        int len = str.lastIndexOf("_145x145.jpg");
                        System.out.println("len "+len);
                        String strPage = str.substring(0, len);
                        System.out.println(" page "+strPage);

    }
    
}
