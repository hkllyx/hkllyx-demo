package com.hkllyx.demo.basic.concurrent.executor.threadpoolexecutor;

import com.hkllyx.demo.basic.concurrent.thread.MyThread;

import java.util.concurrent.*;

/**
 * 一般使用Executor的工厂方法生成对象
 *
 * @author HKLLY
 * @date 2019-07-11
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        // 同ExecutorService exec = Executors.newCachedThreadPool
        ExecutorService exec = new ThreadPoolExecutor(
                // 核心线程数。
                // 添加任务时，当运行的线程数小于核心线程数，就算存在空闲的线程也会新建一个线程
                // 当运行的线程数大于大于核心线程数且小于最大线程数，如果等待列队未满，则入队等待
                0,
                // 最大线程数。
                // 添加任务时，当运行的线程数大于大于核心线程数且小于最大线程数，
                // 如果等待列队已满，则添加一个线程。
                // 如果运行的线程数等于最大线程数，则拒绝任务
                Integer.MAX_VALUE,
                // 保持存活时间，线程池内的线程数超出了核心线程数，
                // 且存在线程在60L(unit)内没有被使用，则将其移除缓存池
                // 注意：设置为0L意味着不会移除
                60L,
                // 保持存活时间的单位
                TimeUnit.SECONDS,
                // 工作列队
                new SynchronousQueue<>(),
                // 线程工厂
                Executors.defaultThreadFactory(),
                // 拒绝策略，处理被拒绝任务
                // CallerRunsPolicy：直接在调用的线程中执行run方法
                // AbortPolicy：只是抛出RejectedExecutionException
                // DiscardPolicy：忽略，什么都不做
                // DiscardOldestPolicy：抛出工作队列中最先入队的任务，然后执行传入的任务
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 5; i++) {
            exec.execute(new MyThread());
        }
        exec.shutdown();
    }
}
