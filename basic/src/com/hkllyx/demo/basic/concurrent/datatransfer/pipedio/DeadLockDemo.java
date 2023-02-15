package com.hkllyx.demo.basic.concurrent.datatransfer.pipedio;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author HKLLY
 * @date 2019-08-22
 */
public class DeadLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try (PipedReader reader = new PipedReader();
                    PipedWriter writer = new PipedWriter(reader)) {
                // 此时管道内没有数据，一直阻塞
                System.out.println("读取资源");
                int i = reader.read();
                System.out.println(i);
                // 无法到达
                System.out.println("写入资源");
                writer.write(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("线程结束");
        });
        t.start();
        t.join();
    }
}
