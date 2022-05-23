package com.hkllyx.demo.basic.concurrent.executor.executors;

import com.hkllyx.demo.basic.concurrent.thread.DaemonThreadFactoryDemo;
import com.hkllyx.demo.basic.concurrent.thread.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1）创建一个单线程执行器，该执行器可以调度命令在给定的延迟之后运行，或者定期执行。<br>
 * 2）如果这个线程在关闭之前的执行过程中由于失败而终止，那么如果需要执行后续任务，将会有一个新的线程代替它。<br>
 * 3）任务保证按顺序执行，并且在任何给定时间都不会有多个任务处于活动状态。<br>
 * 4）保证返回的执行器不可重新配置以使用其他线程。<br>
 *
 * @author HKLLY
 * @date 2019-07-11
 */
public class SingleThreadScheduledExecutor {

    public static void main(String[] args) {
        // ThreadFactory参数可不写
        ExecutorService exec = Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactoryDemo());
        for (int i = 0; i < 5; i++) {
            exec.execute(new MyThread());
        }
        exec.shutdown();
    }
}
