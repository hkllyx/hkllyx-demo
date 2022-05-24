package com.hkllyx.demo.basic.concurrent.datatransfer.blockingqueue.priorityblockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
        pool.execute(new PrioritizedTaskProducer(queue, pool));
        pool.execute(new PrioritizedTaskConsumer(queue));
    }
}
