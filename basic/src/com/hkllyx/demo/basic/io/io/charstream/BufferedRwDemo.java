package com.hkllyx.demo.basic.io.io.charstream;

import java.io.*;

/**
 * @author HKLLY
 * @date 2019-07-17
 */
public class BufferedRwDemo {
    
    public static void main(String[] args) throws IOException {
        File file = new File("src\\main\\resources\\dir1\\demo1.txt");
        writer(new FileWriter(file));
        reader(new FileReader(file));
    }
    
    public static void writer(Writer w) {
        try (BufferedWriter writer = new BufferedWriter(w, 8192)) {
            //向缓冲写入char
            writer.write('A');
            //写入换行符（换行符由平台决定）
            writer.newLine();
            //向缓冲写入String
            String s = "String";
            writer.write(s, 0, s.length());
            //准备mark测试数据
            writer.newLine();
            writer.write("mark&reset test");
            //冲洗缓冲
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void reader(Reader r) {
        try (BufferedReader reader = new BufferedReader(r, 8192)) {
            System.out.println("reader.ready() = " + reader.ready());
            //读取一个char
            System.out.println("reader.read() = " + (char)reader.read());
            //跳过\r\n换行符
            System.out.println("reader.skip(2L) = " + reader.skip(2L));
            //读取一行
            System.out.println("reader.readLine() = " + reader.readLine());
            //测试mark和reset
            System.out.println("reader.markSupported() = " + reader.markSupported());
            int c;
            boolean hasMarked = false;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
                if (c == 'e') {
                    //参数为readLimit，如果标记后读取了超过该个数后mark可能失效
                    reader.mark(100);
                    hasMarked = true;
                }
                if (c == 't' && hasMarked) {
                    //重置到标记，下次读取到的位最近的标记的下一位（'e'的后一位）
                    //如果标记不存在会抛出IO异常
                    reader.reset();
                    hasMarked = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
