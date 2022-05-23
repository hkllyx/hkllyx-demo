package com.hkllyx.demo.basic.concurrent.datatransfer.blockingqueue.priorityblockingqueue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class PrioritizedTaskProducer implements Runnable {
    private final Queue<Runnable> queue;
    private final ExecutorService pool;

    public PrioritizedTaskProducer(Queue<Runnable> queue,
            ExecutorService pool) {
        this.queue = queue;
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            // 加入1-9优先级的任务
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(i));
            }
            // 加入10优先级的任务
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            // 加入尾部哨兵任务
            queue.add(new PrioritizedTask.EndSentinel(pool));
        } catch (InterruptedException e) {
            System.out.println("PrioritizedTaskProducer Interrupted");
        }
        System.out.println("Finished PrioritizedTaskProducer");
    }
}
