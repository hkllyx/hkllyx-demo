package com.hkllyx.demo.basic.concurrent.executor.executors;

import com.hkllyx.demo.basic.concurrent.thread.DaemonThreadFactory;
import com.hkllyx.demo.basic.concurrent.thread.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1）创建一个缓存线程池，该线程池根据需要创建新线程。<br>
 * 2）在前面构造的线程可用时重用它们。<br>
 * 3）通常会提高执行许多短期异步任务的程序的性能。<br>
 * 4）execute()调用将重用以前构造的线程(如果可用)
 * 如果没有可用的现有线程，将创建一个新的线程并将其添加到池中（导致一定开销）。<br>
 * 5）已经60秒没有使用的线程将被终止并从缓存线程池中删除。因此，长时间空闲的池不会消耗任何资源。<br>
 *
 * @author HKLLY
 * @date 2019/4/9
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        // ThreadFactory参数可以忽略
        ExecutorService pool = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 5; i++) {
            pool.execute(new MyThread());
        }
        pool.shutdown();
    }
}