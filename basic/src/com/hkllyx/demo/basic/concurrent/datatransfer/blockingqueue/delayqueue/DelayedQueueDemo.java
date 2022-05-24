package com.hkllyx.demo.basic.concurrent.datatransfer.blockingqueue.delayqueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class DelayedQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService pool = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(random.nextInt(5000)));
        }
        queue.put(new DelayedTask.EndSentinel(5000, pool));
        pool.execute(new DelayTaskConsumer(queue));
    }
}
