package com.hkllyx.demo.basic.concurrent.datatransfer.blockingqueue.priorityblockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
    private static int counter = 0;
    /**
     * 保存优先级任务
     */
    private static List<PrioritizedTask> sequence = new ArrayList<>();
    private final int id = counter++;
    /**
     * 优先级
     */
    private int priority;

    public PrioritizedTask(int priority) {
        this.priority = priority;
        // 将任务加到列表中
        sequence.add(this);
    }

    @Override
    public void run() {
        System.out.println(this + " ");
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
     * 简要信息
     *
     * @return 简要信息
     */
    public String summary() {
        return "(" + id + ":" + priority + ") ";
    }

    /**
     * 尾部哨兵，优先级最低
     */
    public static class EndSentinel extends PrioritizedTask {
        private ExecutorService pool;

        public EndSentinel(ExecutorService pool) {
            super(-1);
            this.pool = pool;
        }

        @Override
        public void run() {
            // 输出优先级列表
            for (PrioritizedTask task : sequence) {
                System.out.print(task.summary());
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            pool.shutdownNow();
        }
    }
}
