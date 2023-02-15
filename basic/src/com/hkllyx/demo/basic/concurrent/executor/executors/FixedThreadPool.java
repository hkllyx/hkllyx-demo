package com.hkllyx.demo.basic.concurrent.executor.executors;

import com.hkllyx.demo.basic.concurrent.thread.DaemonThreadFactory;
import com.hkllyx.demo.basic.concurrent.thread.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 1）创建一个线程池，该线程池在一个共享的无界队列上运行，可以重用固定数量的线程在任何时候。<br>
 * 2）如果在所有线程都处于活动状态时提交新的任务，新任务将在队列中等待，直到有线程可用为止。<br>
 * 3）如果在线程池关闭之前有线程在执行时由于失败而终止，如果需要执行后续任务，将会有一个新的线程代替它。<br>
 * 4）池中的线程将会存在直到调用shutdown()<br>
 *
 * @author HKLLY
 * @date 2019/4/9
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ThreadFactory factory = new DaemonThreadFactory();
        // 一次性预先执行代价高昂的线程分配，限制线程数量。
        // 第一个参数为线程池中线程数量
        // 第二个参数为线程工厂，可以省去，使用Executors的默认线程工厂
        ExecutorService pool = Executors.newFixedThreadPool(5, factory);
        for (int i = 0; i < 5; i++) {
            pool.execute(new MyThread());
        }
        pool.shutdown();
    }
}
