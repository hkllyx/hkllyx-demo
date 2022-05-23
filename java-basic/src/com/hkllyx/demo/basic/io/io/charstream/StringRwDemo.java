package com.hkllyx.demo.basic.io.io.charstream;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 1）StringReader：一个源是String的字符流<br>
 * 2）StringWriter：将其输出收集到字符串缓冲区中的字符流， 可用于构造String。内部是一个StringBuffer<br>
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class StringRwDemo {
    public static void main(String[] args) {
        reader("StringReader");
        System.out.println();
        writer();
    }
    
    public static void reader(String s) {
        StringReader reader = new StringReader(s);
        try {
            System.out.println("reader.ready() = " + reader.ready());
            //读取一个字符
            System.out.println("reader.read() = " + (char)reader.read());
            //跳过一个字符
            System.out.println("reader.skip(1) = " + reader.skip(1));
            //读取一个字符数组
            char[] chars = new char[1024];
            int size = reader.read(chars);
            System.out.println("reader.read(chars) = " + size + ": ");
            System.out.println(new String(chars, 0, size));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("reader.markSupported() = " + reader.markSupported());
    }
    
    public static void writer() {
        //参数表示StringBuffer内部数组的初始大小，默认16
        StringWriter writer = new StringWriter(16);
        writer.write('A');
        writer.write(" String ");
        writer.write("String", 0, 1);
        try {
            //没有重写
            writer.write(" String ".toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write("String".toCharArray(), 0, 1);
        writer.append('A');
        writer.append("CharSequence");
        //第一个参数不是便宜而是start，第二个参数不是长度而是end
        writer.append("CharSequence", 4, 12);
        //获取StringBuffer
        StringBuffer sb = writer.getBuffer();
        System.out.println(sb);
        //啥也不做
        writer.flush();
    }
}
