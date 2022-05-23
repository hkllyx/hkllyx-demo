package com.hkllyx.demo.basic.concurrent.thread;

/**
 * @author HKLLY
 * @date 2019/4/10
 */
public class MyThread extends Thread {
    private static int threadCount = 0;
    private int countDown = 5;

    public MyThread() {
        super("T" + (++threadCount));
    }

    public static void main(String[] args) {
        //  启动 5 个线程
        for (int i = 0; i < 5; i++) {
            new MyThread().start();
        }
    }

    @Override
    public void run() {
        while (countDown >= 0) {
            System.out.print(this);
            countDown--;
        }
    }

    @Override
    public String toString() {
        return getName() + "(" + countDown + "), ";
    }
}
