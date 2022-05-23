package com.hkllyx.demo.basic.io.io.charstream;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * 1）保存行号的缓冲字符输入流。<br>
 * 2）线程安全<br>
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class LineNumberReaderDemo {
    public static void main(String[] args) {
        CharArrayReader r = new CharArrayReader
                ("Line\nNumber\rReader\r\n".toCharArray());
        reader(r);
    }
    
    public static void reader(Reader r) {
        LineNumberReader reader = new LineNumberReader(r, 8192);
        String s;
        try {
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
                reader.getLineNumber();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("reader.getLineNumber() = " + reader.getLineNumber());
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
