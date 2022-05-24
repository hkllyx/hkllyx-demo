package com.hkllyx.demo.basic.io.io.bytestream;

import java.io.*;

/**
 * 1）使用缓冲包装字节流，增强读写效率<br>
 * 2）System.in是一个BufferedInputStream<br>
 * 3）定义和覆盖的方法使用synchronized保证线程安全<br>
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class BufferedStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\main\\resources\\dir1\\demo1.txt");
        out(new FileOutputStream(file));
        System.out.println();
        in(new FileInputStream(file));
    }
    
    public static void out(OutputStream os) {
        //第一个参数为OutputStream，第二个参数为缓冲的大小（可不写，默认为8192）
        try (BufferedOutputStream out = new BufferedOutputStream(os, 1024)) {
            //向缓冲添加一个byte
            out.write(Integer.MAX_VALUE);
            out.write(Integer.MAX_VALUE);
            //向缓冲添加一个byte数组
            byte[] data = "ByteArray".getBytes();
            out.write(data, 0, data.length);
            //准备mark和reset的测试数据
            data = "mark&reset test".getBytes();
            out.write(data);
            //将缓冲冲洗输出（关闭时会自动冲洗）
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void in(InputStream is) {
        //第一个参数为InputStream，第二个参数为缓冲的大小（可不写，默认为8192）
        try (BufferedInputStream in = new BufferedInputStream(is, 1024)) {
            //当前可读或可跳过
            System.out.println("in.available() = " + in.available());
            //跳过一位byte
            System.out.println("in.skip(1) = " + in.skip(1));
            //读取一位byte
            System.out.println("in.read() = " + in.read());
            //读取一个byte数组，返回值为读取到的个数（读取"ByteArray"）
            byte[] data = new byte[9];
            System.out.print("in.read(data, 0, data.length) = "
                    + in.read(data, 0, data.length));
            System.out.println("：" + new String(data));
            
            //是否支持标记
            System.out.println("in.markSupported() = " + in.markSupported());
            //测试mark和reset
            int r;
            boolean hasMarked = false;
            while ((r = in.read()) != -1) {
                System.out.print((char) r);
                if (r == 'e') {
                    //参数为readLimit，如果标记后读取了超过该个数后mark可能失效
                    in.mark(100);
                    hasMarked = true;
                }
                if (r == 't' && hasMarked) {
                    //重置到标记，下次读取到的位最近的标记的下一位（'e'的后一位）
                    //如果标记不存在会抛出IO异常
                    in.reset();
                    hasMarked = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
