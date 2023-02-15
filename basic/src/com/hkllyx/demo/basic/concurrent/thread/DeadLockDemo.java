package com.hkllyx.demo.basic.concurrent.thread;

/**
 * @author HKLLY
 * @date 2019-08-21
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        Object src1 = new Object();
        Object src2 = new Object();
        new Thread(() -> {
            //  获取 src1
            synchronized (src1) {
                System.out.println("Thread-1 获取到了 src1.");
                try {
                    //  等待另一个线程获取了 src2
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread-1 等待 src2.");
                synchronized (src2) {
                    System.out.println("Thread-1 获取到了 src2.");
                }
            }
        }).start();
        new Thread(() -> {
            //  获取 src2
            synchronized (src2) {
                System.out.println("Thread-2 获取到了 src2.");
                try {
                    //  等待另一个线程获取了 src1
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread-2 等待 src1.");
                synchronized (src1) {
                    System.out.println("Thread-2 获取到了 src1.");
                }
            }
        }).start();
    }
}
