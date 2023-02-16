package com.hkllyx.demo.basic.concurrent.synchronizer.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author HKLLY
 * @date 2019-08-21
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        final CountDownLatch signal1 = new CountDownLatch(1);
        final CountDownLatch signal2 = new CountDownLatch(1);

        new Thread(() -> {
            try {
                System.out.println("等待 signal2");
                signal2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                signal1.countDown();
            }
            System.out.println("线程 1 结束");
        }).start();

        new Thread(() -> {
            try {
                System.out.println("等待 signal1");
                signal1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                signal2.countDown();
            }
            System.out.println("线程 2 结束");
        }).start();
    }
}
