package com.hkllyx.demo.basic.concurrent.synchronizer.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author HKLLY
 * @date 2019-08-21
 */
public class Worker implements Runnable {
    private static int count = 0;
    private final int id = count++;

    /** 启动信号，在 driver 为继续执行 worker 做好准备之前，它会阻止所有的 worker 继续执行。 */
    private final CountDownLatch startSignal;
    /** 完成信号，保证 driver 在完成所有 worker 之前一直等待。 */
    private final CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            //  等待启动信号
            startSignal.await();
            //  模拟工作
            System.out.println("Work " + id + " finished work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //  将完成信号计数 -1
            doneSignal.countDown();
        }
    }
}
