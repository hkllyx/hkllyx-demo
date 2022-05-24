package com.hkllyx.demo.basic.concurrent.executor.threadpoolexecutor;

import com.hkllyx.demo.basic.concurrent.thread.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ScheduledThreadPoolExecutor继承自ThreadPoolExecutor
 * 一般用Executors的工厂方法生成对象
 *
 * @author HKLLY
 * @date 2019-07-11
 */
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        // 同ExecutorService exec = Executors.newScheduledThreadPool(5);
        ExecutorService exec = new ScheduledThreadPoolExecutor(
                // 核心线程数
                5,
                // 线程工厂
                Executors.defaultThreadFactory(),
                // 拒绝处理
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 5; i++) {
            exec.execute(new MyThread());
        }
        exec.shutdown();
    }
}
