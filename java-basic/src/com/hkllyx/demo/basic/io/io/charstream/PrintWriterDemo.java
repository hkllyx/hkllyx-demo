package com.hkllyx.demo.basic.io.io.charstream;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 功能类似PrintStream
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class PrintWriterDemo {
    public static void main(String[] args) throws IOException {
        print("src\\main\\resources\\dir1\\demo1.txt");
    }
    
    public static void print(String filename) {
        try (PrintWriter print = new PrintWriter(filename, "UTF-8")) {
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
            print.write("ByteArray".toCharArray());
            print.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
