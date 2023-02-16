package com.hkllyx.demo.basic.io.io.bytestream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 1）SequenceInputStream使用一个Enumeration保存一个序列的InputStream<br>
 * 2）read(int)方法按顺序读取InputStream，当所有InputStream读取完才会读到-1（EOF）<br>
 * 3）available()只能返回当前读取的InputStream的可可读或可跳过字节数<br>
 * 4）read(byte[], int, int)只能读取当前正在读的InputStream<br>
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) {
        int nIs = 5;
        Vector<InputStream> isVector = new Vector<>(nIs);
        for (int i = 0; i < nIs; i++) {
            isVector.add(new ByteArrayInputStream(("IS" + i).getBytes()));
        }
        in(isVector.elements());
    }
    
    public static void in(Enumeration<InputStream> isEnumeration) {
        try (SequenceInputStream in = new SequenceInputStream(isEnumeration)) {
            //available()只能返回当前读取的InputStream的可可读或可跳过字节数（结果为3而不是15）
            System.out.println("in.available() = " + in.available());
            byte[] data = new byte[15];
            //read(byte[], int, int)只能读取当前正在读的InputStream（size为3而不是15）
            int size = in.read(data);
            System.out.println(new String(data, 0, size));
            //读取int时则可以读全部剩下的InputStream序列
            int i;
            while ((i = in.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
