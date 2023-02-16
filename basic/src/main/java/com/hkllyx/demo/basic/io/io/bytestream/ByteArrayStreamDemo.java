package com.hkllyx.demo.basic.io.io.bytestream;

import java.io.*;
import java.util.Arrays;

/**
 * 1）基本的字节流<br>
 * 2）out写的字节存放到缓冲中，缓冲自动增长（2倍）<br>
 * 3）out实际上没有开启输出流。无需关闭<br>
 * 4）in实际上没有开启输入流，只是接受了一个byte[]数组，
 * 这个数组可以是另一个输入流从文件读取的，也可以是自定义的。无需关闭
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class ByteArrayStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\main\\resources\\dir1\\demo1.txt");
        out(new FileOutputStream(file));
        System.out.println();
        in(new FileInputStream(file));
    }
    
    public static void out(OutputStream os) {
        //参数表示初始缓冲大小，可不写，默认32
        ByteArrayOutputStream out = new ByteArrayOutputStream(32);
        //向缓冲添加一个int（会强转为byte）
        out.write(255);
        //向缓冲添加一个数组
        byte[] data = "ByteArray".getBytes();
        //没有重写
        try {
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(data, 0, data.length);
        //自身无法输出，只能将缓冲用另一个OutputStream输出
        try {
            out.writeTo(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //转为字符串
        System.out.println("out.toString() = " + out.toString());
        //转为字符串，指定编码
        try {
            System.out.println("out.toString(\"UTF-8\") = " 
                    + out.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转为byte数组
        System.out.println("out.toByteArray() = " 
                + Arrays.toString(out.toByteArray()));
        //获取当前缓冲大小
        System.out.println("out.size() = " + out.size());
        //重置缓冲
        out.reset();
        System.out.println("out.size() = " + out.size());
        //冲洗缓冲，没有重写
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void in(InputStream is) {
        byte[] source = new byte[1024];
        try {
            //自身无法真正读取，只能通过其他InputStream来读取
            is.read(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //后两个参数表偏移和长度，可不写表示偏移为0，长度为data.length
        ByteArrayInputStream in = new ByteArrayInputStream(source, 0, source.length);
        System.out.println("in.available() = " + in.available());
        System.out.println("in.read() = " + in.read());
        System.out.println("in.skip(1L) = " + in.skip(1L));
        byte[] data = new byte[1024];
        //read(byte[])未重写，会抛出异常，而read(byte[]， int， int)重写了，不会抛出异常
        System.out.println("in.read(data， 0， data.length) = "
                + in.read(data, 0, data.length));
    }
}
