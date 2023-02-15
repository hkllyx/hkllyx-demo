package com.hkllyx.demo.basic.concurrent.synchronizer.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiaoyong3
 * @date 2022/06/22
 */
class Driver {
    private final int workNum;

    public Driver(int workNum) {
        this.workNum = workNum;
    }

    public void start() throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(workNum);

        for (int i = 0; i < workNum; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        System.out.println("Before workers start work");
        //  启动所以 worker
        startSignal.countDown();
        System.out.println("Start all workers");
        //  等待所以 worker 完成
        doneSignal.await();
        System.out.println("All workers done");
    }
}
