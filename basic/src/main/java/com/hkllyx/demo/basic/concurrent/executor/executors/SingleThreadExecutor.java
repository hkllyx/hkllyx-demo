package com.hkllyx.demo.basic.concurrent.executor.executors;

import com.hkllyx.demo.basic.concurrent.thread.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1）创建一个执行器，该执行器使用一个工作线程操作一个无界队列。<br>
 * 2）如果这个单线程在关闭之前的执行过程中由于失败而终止，那么如果需要执行后续任务，将会有一个新的线程替代它。<br>
 * 3）任务保证按顺序执行，并且在任何给定的时间内不会有多个任务处于活动状态。<br>
 *
 * @author HKLLY
 * @date 2019/4/9
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        // 相当于线程为1的FixedThreadPool
        // ThreadFactory参数可不写
        ExecutorService exec = Executors.newSingleThreadExecutor(new DaemonThreadFactory());
        for (int i = 0; i < 5; i++) {
            exec.execute(System.out::println);
        }
        exec.shutdown();
    }
}