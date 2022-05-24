package com.hkllyx.demo.basic.concurrent.datatransfer.blockingqueue.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class DelayTaskConsumer implements Runnable {
    private final DelayQueue<DelayedTask> queue;

    public DelayTaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("DelayedTaskConsumer Interrupted");
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}
