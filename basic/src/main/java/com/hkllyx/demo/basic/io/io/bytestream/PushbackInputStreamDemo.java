package com.hkllyx.demo.basic.io.io.bytestream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;

/**
 * 1）退回流读取到指定的一个值或一组值时，可以退回读取前的位置，给用户第二次读取的机会<br>
 * 2）可以利用该机制跳过读取某些值，或改变其值<br>
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class PushbackInputStreamDemo {
    
    public static void main(String[] args) {
        //准备读取的数据
        ByteArrayInputStream is = new ByteArrayInputStream
                ("PushbackInputStream".getBytes());
        //测试PushbackInputStream
        unreadByte(is);
        System.out.println();
        is = new ByteArrayInputStream("PushbackInputStream".getBytes());
        unreadBytes(is);
    }
    
    public static void unreadByte(InputStream is) {
        //退回一个byte
        //第二个参数为缓冲大小，可不写，默认1
        try (PushbackInputStream in = new
                PushbackInputStream(is, is.available())) {
            int i;
            while ((i = in.read()) != -1) {
                if (i == 'a') {
                    //如果读取到a，退回
                    //读取位置向前移动一位，并将该位的值设为(byte)i
                    in.unread(i);
                    //再读一次，防止下次循环再次读取'a'导致死循环
                    System.out.print("(" + (char) in.read() + ")");
                } else {
                    System.out.print((char) i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void unreadBytes(InputStream is) {
        //退回一个byte数组
        try (PushbackInputStream in = new
                PushbackInputStream(is, is.available())) {
            byte[] data = new byte[2];
            //指定不读取的数组
            byte[] unread = new byte[] {'I', 'n'};
            //读到EOF为止
            while (in.available() != 0) {
                //记录当前读取到的数组的size
                int size = in.read(data);
                //只有data读取完整才能进行比较，否则data可能残余上一次读取结果
                if (size == data.length) {
                    if (Arrays.equals(data, unread)) {
                        in.unread(data, 0, data.length);
                        in.read(data);
                        System.out.print("(" + new String(data) + ")");
                    } else {
                        System.out.print(new String(data));
                    }
                } else {
                    System.out.println(new String(data, 0, size));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
