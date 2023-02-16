package com.hkllyx.demo.basic.io.io.charstream;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019-07-17
 */
public class PipedRwDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = ExecutorUtils.newFixedThreadPool(2);
        //创建管道输出流，可以传入一个PipedReader
        PipedWriter writer = new PipedWriter();
        //创建管道输入流，可PipedOutputStream可以不写，
        //第二个参数为管道大小，也可以不写，默认1024
        PipedReader reader = new PipedReader(writer, 1024);
        //管道输入输出流均有一个connect方法可以连接双方，
        //其中输入流内部调用输出流的connect来实现
        
        //执行线程
        pool.execute(new WriterThread(writer));
        pool.execute(new ReaderThread(reader));
        pool.shutdown();
        TimeUnit.SECONDS.sleep(1);
        
        writer.close();
        reader.close();
    }
    
    static class WriterThread implements Runnable {
        PipedWriter writer;
        
        public WriterThread(PipedWriter writer) {
            this.writer = writer;
        }
        
        @Override
        public void run() {
            try {
                for (char c = 'A'; c < 'Z'; c++) {
                    //输出一个(char)int
                    writer.write(c);
                }
                //输出数组
                char[] data = "CharArray".toCharArray();
                writer.write(data, 0, data.length);
                //冲洗（目的是唤醒接收线程）
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class ReaderThread implements Runnable {
        PipedReader reader;
        
        public ReaderThread(PipedReader reader) {
            this.reader = reader;
        }
        
        @Override
        public void run() {
            try {
                //System.out.println("reader.ready() = " + reader.ready());
                //接受int
                for (char c = 'A'; c < 'Z'; c++) {
                    System.out.print((char)reader.read());
                }
                //接受char数组
                char[] data = new char[1024];
                int size = reader.read(data);
                System.out.println(new String(data, 0, size));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
