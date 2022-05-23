package com.hkllyx.demo.basic.io.io.bytestream;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 1）一个管道输出流连接一个管道输入流，从而实现通信管道<br>
 * 2）可以使用管道通信实现两个线程之间的通信<br>
 * 3）因为管道输入输出流的方法中以对方作为锁来保证同步， 所以不建议在同一个线程同时使用管道输入输出流，可能会造成死锁<br>
 * 4）当通信的一方线程死亡，通信管道也随之破裂<br>
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class PipedStreamDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = ExecutorUtils.newFixedThreadPool(2);
        //创建管道输出流，可以传入一个PipedInputStream
        PipedOutputStream out = new PipedOutputStream();
        //创建管道输入流，可PipedOutputStream可以不写，
        //第二个参数为管道大小，也可以不写，默认1024
        PipedInputStream in = new PipedInputStream(out, 1024);
        //管道输入输出流均有一个connect方法可以连接双方，
        //其中输入流内部调用输出流的connect来实现
        
        //执行线程
        pool.execute(new OutputStreamThread(out));
        pool.execute(new InputStreamThread(in));
        pool.shutdown();
        TimeUnit.SECONDS.sleep(1);
        
        //关闭流
        out.close();
        in.close();
    }
    
    static class OutputStreamThread implements Runnable {
        PipedOutputStream out;
        
        public OutputStreamThread(PipedOutputStream out) {
            this.out = out;
        }
        
        @Override
        public void run() {
            try {
                for (char c = 'A'; c < 'Z'; c++) {
                    //输出一个(byte)int
                    out.write(c);
                }
                //输出数组
                byte[] data = "ByteArray".getBytes();
                out.write(data, 0, data.length);
                //冲洗（目的是唤醒接收线程）
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class InputStreamThread implements Runnable {
        PipedInputStream in;
        
        public InputStreamThread(PipedInputStream in) {
            this.in = in;
        }
        
        @Override
        public void run() {
            try {
                //接受int
                for (char c = 'A'; c < 'Z'; c++) {
                    System.out.print((char)in.read());
                }
                //接受byte数组
                byte[] data = new byte[in.available()];
                in.read(data, 0, data.length);
                System.out.println(new String(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
