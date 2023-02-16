package com.hkllyx.demo.basic.io.io.charstream;

import java.io.*;
import java.util.Arrays;

/**
 * 1）基本字符流<br>
 * 2）数据在保存在对象的缓冲数组中<br>
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class CharArrayRwDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("src\\main\\resources\\dir1\\demo1.txt");
        writer(new FileWriter(file));
        System.out.println();
        reader(new FileReader(file));
    }
    
    public static void writer(Writer w) {
        //参数表示缓冲大小，可不写，默认32
        CharArrayWriter writer = new CharArrayWriter(32);
        //向流中写入一个(char)int，截取后两个byte
        writer.write('A');
        //向流中写入一个字符串
        String s = "String";
        try {
            //没有重写
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(s, 1, s.length() - 1);
        //向流中写入一个char数组
        char[] chars = "chars".toCharArray();
        try {
            //没有重写
            writer.write(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(chars, 1, chars.length - 1);
        //追加字符或字符串
        writer.append('A');
        writer.append("CharSequence");
        //第一个参数不是便宜而是start，第二个参数不是长度而是end
        writer.append("CharSequence", 4, 12);
        //将缓冲的数据传递给另一个writer
        try {
            writer.writeTo(w);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //冲洗缓冲（啥也没做）
        writer.flush();
        //获取缓冲大小
        System.out.println("writer.size() = " + writer.size());
        //转为char数组
        System.out.println("writer.toCharArray() = "
                + Arrays.toString(writer.toCharArray()));
        //转为字符串
        System.out.println("writer.toString() = " + writer.toString());
        //重置缓冲
        writer.reset();
    }
    
    public static void reader(Reader r) {
        //获取数据
        char[] source = new char[1024];
        int size = 0;
        try {
            size = r.read(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //后面两个参数可以忽略，表示传入整个数组
        CharArrayReader reader = new CharArrayReader(source, 0, size);
        //是否支持mark
        System.out.println("reader.markSupported() = " + reader.markSupported());
        //读取char
        try {
            System.out.println("reader.read() = " + reader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取char数组
        char[] chars = new char[1024];
        try {
            size = reader.read(chars);
            System.out.println("reader.read(chars) = " + size + ": ");
            System.out.println(new String(chars, 0, size));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //重置reader
        try {
            reader.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
