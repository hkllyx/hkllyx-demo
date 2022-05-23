package com.hkllyx.demo.basic.io.io.bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 基本的字节流
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class FileStreamDemo {
    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\dir1\\demo1.txt");
        out(file);
        System.out.println();
        in(file);
    }
    
    public static void out(File file) {
        //第一个参数可为File和File的路径，第二参数append表示是否在原文件后追加内容
        //第二个参数可不写，表示append为false（从清空文件从开头输出）
        //此外，FileOutputStream可以用一个FileDescriptor作为参数
        //！使用try()的auto close机制，可以不用在finally中手动close
        try (FileOutputStream out = new FileOutputStream(file, false)) {
            //向文件中输出一个int（只输出最后一个byte而非4个）
            out.write(Integer.MAX_VALUE);
            out.write(Byte.MAX_VALUE);
            byte[] data = "ByteArray".getBytes();
            //向文件中输出一个byte数组，后两个个参数为偏移和输出的长度
            //后两个参数可以不写，表示偏移为0，输出长度为数组长度（即输出整个数组）
            out.write(data, 0, data.length);
            //冲洗out，将缓冲的byte数组输出
            out.flush();
            //获取NIO的FileChannel对象
            System.out.println("out.getChannel() = " + out.getChannel());
            //获取FileDescriptor对象
            System.out.println("out.getFD() = " + out.getFD());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void in(File file) {
        //参数可以为File或File的路径
        //此外，FileInputStream可以用一个FileDescriptor作为参数
        try (FileInputStream in = new FileInputStream(file)) {
            //查看可读或可跳过的byte的数量
            System.out.println("in.available() = " + in.available());
            //读取一个byte
            System.out.println("in.read() = " + in.read());
            //验证输出时的截取最后一个byte
            System.out.println("(Integer.MAX_VALUE & 0x0000003f) = "
                    + (Integer.MAX_VALUE & 0x000000ff));
            //跳过读取n个byte
            System.out.println("in.skip(1) = " + in.skip(1));
            //读取一个byte数组，返回值为读取到的个数（读取"ByteArray"）
            byte[] data = new byte[9];
            System.out.print("in.read(data, 0, data.length) = "
                    + in.read(data, 0, data.length));
            System.out.println("：" + new String(data));
            //读取到文件尾（EOF）返回-1
            System.out.println("in.read() = " + in.read());
            //查看可读或可跳过byte的数量
            System.out.println("in.available() = " + in.available());
            //获取NIO的FileChannel对象
            System.out.println("in.getChannel() = " + in.getChannel());
            //获取FileDescriptor对象
            System.out.println("in.getFD() = " + in.getFD());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
