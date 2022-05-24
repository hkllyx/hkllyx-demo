package com.hkllyx.demo.basic.io.io.charstream;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 基本的字符流
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class IoStreamRwDemo {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\main\\resources\\dir1\\demo1.txt");
        writer(new FileOutputStream(file));
        System.out.println();
        reader(new FileInputStream(file));
    }
    
    public static void writer(OutputStream os) {
        //第一个参数为字节输出流，第二个参数为字符集
        //（指定编码，可以使用其名称String和CharsetDecoder对象代替）
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            //获取字符流的编码
            System.out.println("writer.getEncoding() = " + writer.getEncoding());
            //向流中写入一个(char)int，截取后两个byte
            writer.write('A');
            //向流中写入一个字符串
            String s = "String";
            writer.write(s);
            writer.write(s, 1, s.length() - 1);
            //向流中写入一个char数组
            char[] chars = "chars".toCharArray();
            writer.write(chars);
            writer.write(chars, 1, chars.length - 1);
            //追加字符或字符串
            writer.append('A');
            writer.append("CharSequence");
            //第一个参数不是便宜而是start，第二个参数不是长度而是end
            writer.append("CharSequence", 4, 12);
            //冲洗缓冲，将流中的数据输出
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void reader(InputStream is) {
        //第一个参数为字节输入流，第二个参数为字符集
        //（指定编码，可以使用其名称String和CharsetDecoder对象代替）
        try (InputStreamReader reader =
                     new InputStreamReader(is, StandardCharsets.UTF_8)) {
            //查看编码
            System.out.println("reader.getEncoding() = " + reader.getEncoding());
            //查看输入流是否准备好（可读）
            System.out.println("reader.ready() = " + reader.ready());
            //读取一个char
            System.out.println("reader.read() = " + (char)reader.read());
            //读取char数组
            char[] chars = new char[1024];
            int size = reader.read(chars, 0, chars.length);
            System.out.println(new String(chars, 0, size));
            //跳过几个字符
            System.out.println("reader.skip(1) = " + reader.skip(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
