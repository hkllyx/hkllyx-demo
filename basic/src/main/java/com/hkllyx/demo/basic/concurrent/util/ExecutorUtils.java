package com.hkllyx.demo.basic.concurrent.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用于创建Executor对象
 *
 * @author HKLLY
 * @date 2019-07-12
 */
public class ExecutorUtils {
    /**
     * 默认线程工厂
     */
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);
        private final ThreadGroup threadGroup = new ThreadGroup("default-thread-group");

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(threadGroup, r);
            thread.setName("thread-pool-" + counter.incrementAndGet());
            return thread;
        }
    };
    /**
     * 默认拒绝处理策略
     */
    private static final RejectedExecutionHandler
            DEFAULT_REJECTED_EXECUTION_HANDLER = new ThreadPoolExecutor.AbortPolicy();

    private ExecutorUtils() {
    }

    /**
     * 创建一个SingleThreadExecutor
     *
     * @return SingleThreadExecutor
     */
    public static ThreadPoolExecutor newSingleThreadExecutor() {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.NANOSECONDS,
                new LinkedBlockingQueue<>(1),
                DEFAULT_THREAD_FACTORY,
                DEFAULT_REJECTED_EXECUTION_HANDLER);
    }

    /**
     * 创建一个指定线程数的FixedThreadPool
     *
     * @param nThread 核心线程数和最大线程数
     * @return FixedThreadPool
     */
    public static ThreadPoolExecutor newFixedThreadPool(int nThread) {
        return new ThreadPoolExecutor(nThread, nThread,
                0L, TimeUnit.NANOSECONDS,
                new LinkedBlockingQueue<>(),
                DEFAULT_THREAD_FACTORY,
                DEFAULT_REJECTED_EXECUTION_HANDLER);
    }

    /**
     * 创建一个CachedThreadPool
     *
     * @return CachedThreadPool
     */
    public static ThreadPoolExecutor newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                DEFAULT_THREAD_FACTORY,
                DEFAULT_REJECTED_EXECUTION_HANDLER);
    }

    /**
     * 创建一个自定义的ThreadPoolExecutor
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   保持存活时间
     * @param unit            时间单位
     * @param workQueue       工作列队
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor
    newThreadPoolExecutor(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                DEFAULT_THREAD_FACTORY,
                DEFAULT_REJECTED_EXECUTION_HANDLER);
    }

    /**
     * 创建一个自定义的ThreadPoolExecutor
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   保持存活时间
     * @param unit            时间单位
     * @param workQueue       工作列队
     * @param threadFactory   线程工厂
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor
    newThreadPoolExecutor(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                DEFAULT_REJECTED_EXECUTION_HANDLER);
    }

    /**
     * 创建一个自定义的ThreadPoolExecutor
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   保持存活时间
     * @param unit            时间单位
     * @param workQueue       工作列队
     * @param threadFactory   线程工厂
     * @param handler         拒绝处理策略
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor
    newThreadPoolExecutor(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler);
    }

    /**
     * 创建一个SingleThreadScheduledExecutor
     *
     * @return SingleThreadScheduledExecutor
     */
    public static ScheduledThreadPoolExecutor newSingleThreadScheduledExecutor() {
        return new ScheduledThreadPoolExecutor(1,
                DEFAULT_THREAD_FACTORY,
                DEFAULT_REJECTED_EXECUTION_HANDLER);
    }

    /**
     * 创建一个指定核心线程数的newScheduledThreadPool
     *
     * @param corePoolSize 核心线程数
     * @return newScheduledThreadPool
     */
    public static ScheduledThreadPoolExecutor newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                DEFAULT_THREAD_FACTORY,
                DEFAULT_REJECTED_EXECUTION_HANDLER);
    }

    /**
     * 创建一个自定义的ScheduledThreadPoolExecutor
     *
     * @param corePoolSize  核心线程数
     * @param threadFactory 线程工厂
     * @return ScheduledThreadPoolExecutor
     */
    public static ScheduledThreadPoolExecutor
    newScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                threadFactory,
                DEFAULT_REJECTED_EXECUTION_HANDLER);
    }

    /**
     * 创建一个自定义的ScheduledThreadPoolExecutor
     *
     * @param corePoolSize  核心线程数
     * @param threadFactory 线程工厂
     * @param handler       拒绝处理策略
     * @return ScheduledThreadPoolExecutor
     */
    public static ExecutorService
    newScheduledThreadPoolExecutor(int corePoolSize,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler) {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                threadFactory,
                handler);
    }
}
