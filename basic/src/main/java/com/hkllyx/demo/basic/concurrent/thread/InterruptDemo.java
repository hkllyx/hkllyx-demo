package com.hkllyx.demo.basic.concurrent.thread;

/**
 * @author HKLLY
 * @date 2019-08-20
 */
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted by something!");
                } else {
                    System.out.println("Thread is going...");
                }
            }
        });

        t.start();
        Thread.sleep(100);
        //  并不会真正中断线程，调用后会一直输出"Interrupted by something!"
        t.interrupt();
    }
}
