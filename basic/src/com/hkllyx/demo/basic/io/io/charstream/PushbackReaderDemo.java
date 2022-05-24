package com.hkllyx.demo.basic.io.io.charstream;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

/**
 * 功能同PushbackInputStream
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class PushbackReaderDemo {
    
    public static void main(String[] args) {
        //准备读取的数据
        CharArrayReader reader = new CharArrayReader("PushbackReader".toCharArray());
        //测试PushbackInputStream
        unread(reader);
    }
    
    public static void unread(Reader r) {
        //第二个参数为缓冲大小，可不写，默认1
        try (PushbackReader reader = new PushbackReader(r, 1)) {
            int i;
            while ((i = reader.read()) != -1) {
                if (i == 'a') {
                    //如果读取到a，退回
                    //读取位置向前移动一位，并将该位的值设为(char)i
                    reader.unread(i);
                    //再读一次，防止下次循环再次读取'a'导致死循环
                    System.out.print("(" + (char)reader.read() + ")");
                } else {
                    System.out.print((char)i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
