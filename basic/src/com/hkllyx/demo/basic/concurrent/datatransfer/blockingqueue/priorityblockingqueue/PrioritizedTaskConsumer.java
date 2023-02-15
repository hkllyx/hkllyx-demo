package com.hkllyx.demo.basic.concurrent.datatransfer.blockingqueue.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class PrioritizedTaskConsumer implements Runnable {
    /**
     * 消费者任务优先级队列（线程安全）
     */
    private final PriorityBlockingQueue<Runnable> queue;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 取出一个任务运行
                queue.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("PrioritizedTaskConsumer Interrupted");
        }
        System.out.println("Finished PrioritizedTaskConsumer");
    }
}
