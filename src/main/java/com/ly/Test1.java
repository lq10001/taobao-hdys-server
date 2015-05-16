package com.ly;

/**
 * Created by zhongwei on 5/16/15.
 */
public class Test1 {

    public static void main(String[] argv)
    {
        String url = "//img.alicdn.com/bao/uploaded/i3/263817957/TB2z3M9cFXXXXXiXXXXXXXXXXXX-263817957.jpg_480x480q50.jpg";
        int len2 = url.indexOf(".jpg");
        System.out.println(url.substring(0, len2));

    }


}
