package com.hkllyx.demo.basic.io.io.bytestream;

import java.io.*;

/**
 * 1）自身处理了异常，所以永远不会抛出异常<br>
 * 2）可以设置是否自动冲洗缓冲<br>
 * 3）println方法可以自动在末尾输出'\n'<br>
 * 4）System.out就是一个PrintStream对象<br>
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        print(new FileOutputStream("src\\main\\resources\\dir1\\demo1.txt"));
    }
    
    public static void print(OutputStream os) {
        //第一个参数为OutputStream，第二个为是否自动冲洗，第三个为编码
        //第一个OutputStream可以直接换成文件路径
        //后两个可不写，自动冲洗默认为false，编码会使用平台默认
        try (PrintStream print = new PrintStream(os, true, "UTF-8")) {
            print.println(Byte.MAX_VALUE);
            print.println(Character.MAX_VALUE);
            print.println(Short.MAX_VALUE);
            print.println(Integer.MAX_VALUE);
            print.println(Long.MAX_VALUE);
            print.println(Float.MAX_VALUE);
            print.println(Double.MAX_EXPONENT);
            print.println(true);
            print.println(new char[]{'c', 'h', 'a', 'r', '[', ']'});
            print.println("String");
            print.println(print);
            //格式化输出
            print.printf("%2$s %1$s\n", "print", "format");
            //追加
            print.append('c');
            print.append("CharSequence", 0, "CharSequence".length());
            //检查错误
            System.out.println("print.checkError() = " + print.checkError());
            //覆盖的方法
            print.write(Integer.MAX_VALUE);
            print.write("ByteArray".getBytes());
            print.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
