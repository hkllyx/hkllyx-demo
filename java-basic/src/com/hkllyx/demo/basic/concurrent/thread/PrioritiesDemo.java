package com.hkllyx.demo.basic.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 优先级演示
 *
 * @author HKLLY
 * @date 2019/4/9
 */
public class PrioritiesDemo implements Runnable {
    private int countDown = 5;
    /**
     * volatile 保证程序不被编译器优化，如果去掉run()中对于d的操作或此关键字
     * 低优先级线程不一定需要在所有高优先级线程结束后执行
     */
    private volatile double d;
    private final int priority;

    public PrioritiesDemo(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Priority: " + Thread.currentThread().getPriority() + ", CountDown: " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new PrioritiesDemo(Thread.MIN_PRIORITY));
            exec.execute(new PrioritiesDemo(Thread.MAX_PRIORITY));
        }
        exec.shutdown();
    }
}