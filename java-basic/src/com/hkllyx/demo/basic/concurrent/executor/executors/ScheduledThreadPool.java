package com.hkllyx.demo.basic.concurrent.executor.executors;

import com.hkllyx.demo.basic.concurrent.thread.DaemonThreadFactoryDemo;
import com.hkllyx.demo.basic.concurrent.thread.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个线程池，该线程池可以调度在给定延迟之后运行的命令，或者定期执行命令。
 *
 * @author HKLLY
 * @date 2019-07-11
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        // ThreadFactory参数可不写
        ExecutorService pool = Executors.newScheduledThreadPool(5, new DaemonThreadFactoryDemo());
        for (int i = 0; i < 5; i++) {
            pool.execute(new MyThread());
        }
        pool.shutdown();
    }
}
