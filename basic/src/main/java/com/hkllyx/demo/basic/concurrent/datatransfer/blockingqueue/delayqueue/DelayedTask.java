package com.hkllyx.demo.basic.concurrent.datatransfer.blockingqueue.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    /**
     * 延迟时间
     */
    private final int delta;
    /**
     * 记录启动时间
     */
    private final long trigger;

    public DelayedTask(int delayInMilliSeconds) {
        delta = delayInMilliSeconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
    }

    /**
     * 比较启动时间，确定启动优先级
     *
     * @param o 比较者
     * @return 启动时间比较结果
     */
    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        return Long.compare(trigger, that.trigger);
    }

    @Override
    public void run() {
        System.out.println(this);
    }

    /**
     * 获取还需要等待多时时间启动
     *
     * @param unit 时间单位
     * @return 还需要等待的时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d] Task  %2$d", delta, id);
    }

    /**
     * 简要概述
     *
     * @return 简要概述
     */
    public String summary() {
        return "(" + id + ":" + delta + ") ";
    }

    /**
     * 结束时统计每个线程的启动延时
     */
    public static class EndSentinel extends DelayedTask {
        private final ExecutorService pool;

        public EndSentinel(int delayInMilliSeconds, ExecutorService pool) {
            super(delayInMilliSeconds);
            this.pool = pool;
        }

        @Override
        public void run() {
            System.out.println(this + " Calling shutdownNow()");
            pool.shutdownNow();
        }
    }
}
