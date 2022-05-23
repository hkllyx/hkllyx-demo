package com.hkllyx.demo.basic.io.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * UTF-8编码下的URL只能接收[0-9a-zA-Z*_-.+]内的字符
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class UrlEncoderDecoderDemo {
    
    public static void main(String[] args) {
        String s = "0123456789" +
                "abcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "~!@#$%^&*_+=-`|:\"<>?\\;',./ (){}[]"
                + "汉字";
        try {
            //使用UTF-8编码成URL可接受的字符串
            s = URLEncoder.encode(s, "UTF-8");
            System.out.println(s);
            //使用UTF-8将URL解码
            s = URLDecoder.decode(s, "UTF-8");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
