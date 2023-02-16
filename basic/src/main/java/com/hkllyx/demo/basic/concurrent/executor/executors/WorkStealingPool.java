package com.hkllyx.demo.basic.concurrent.executor.executors;

import com.hkllyx.demo.basic.concurrent.thread.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1）工作窃取（work-stealing）算法是指某个线程从其他队列里窃取任务来执行。<br>
 * 2）一个比较大的任务可以分割为若干互不依赖的子任务。<br>
 * 3）为了减少线程间的竞争，于是把这些子任务分别放到不同的队列里，
 * 并为每个队列创建一个单独的线程来执行队列里的任务，线程和队列一一对应<br>
 * 4）有的线程先做完任务，它就可以去其他线程的队列里窃取一个任务来执行。<br>
 * 5）窃取任务时，窃取线程和被窃取线程会访问同一个队列，
 * 所以为了减少窃取任务线程和被窃取任务线程之间的竞争，通常会使用双端队列，
 * 被窃取任务线程永远从双端队列的头部拿任务执行，而窃取任务的线程永远从双端队列的尾部拿任务执行。<br>
 * 6）优缺点：优点是充分利用线程进行并行计算，并减少了线程间的竞争，
 * 其缺点是在某些情况下还是存在竞争，比如双端队列里只有一个任务时。
 * 并且消耗了更多的系统资源，比如创建多个线程和多个双端队列。
 *
 * @author HKLLY
 * @date 2019-07-12
 */
public class WorkStealingPool {

    public static void main(String[] args) {
        // 参数表示并行级别
        ExecutorService exec = Executors.newWorkStealingPool(5);
        for (int i = 0; i < 5; i++) {
            int[] nums = new int[5 - i];
            for (int j = 0; j < nums.length; j++) {
                nums[j] = j;
            }
            // ForkJoinPool会将Runnable包装成返回值为null的ForkJoinTask
            exec.submit(new MyThread());
        }
        exec.shutdown();
    }
}
