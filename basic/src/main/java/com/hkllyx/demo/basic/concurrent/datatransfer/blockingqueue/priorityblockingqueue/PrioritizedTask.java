package com.hkllyx.demo.basic.concurrent.datatransfer.blockingqueue.priorityblockingqueue;

import java.util.concurrent.ExecutorService;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
    private static int counter = 0;
    private final int id = counter++;
    /**
     * 优先级
     */
    private final int priority;

    public PrioritizedTask(int priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        System.out.println(this);
    }

    @Override
    public int compareTo(PrioritizedTask o) {
        // 优先级比较
        return Integer.compare(priority, o.priority);
    }

    @Override
    public String toString() {
        return String.format("[%1$2d] Task  %2$d", priority, id);
    }

    /**
     * 尾部哨兵，优先级最低
     */
    public static class EndSentinel extends PrioritizedTask {
        private final ExecutorService pool;

        public EndSentinel(ExecutorService pool) {
            super(-1);
            this.pool = pool;
        }

        @Override
        public void run() {
            System.out.println(this + " Calling shutdownNow()");
            pool.shutdownNow();
        }
    }
}
