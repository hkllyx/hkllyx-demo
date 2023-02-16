package com.hkllyx.demo.basic.concurrent.threadlocal;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 1）ThreadLocal是一个关于创建线程局部变量的类。
 * 2）通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。
 * 而使用ThreadLocal创建的变量只能被当前线程访问，其他线程则无法访问和修改。
 * 3）Global && Local：Global意思是在当前线程中，任何一个点都可以访问到ThreadLocal的值。
 * Local意思是该线程的ThreadLocal只能被该线程访问，一般情况下其他线程访问不到
 *
 * @author HKLLY
 * @date 2019-07-12
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        //  虽然 local 在 main 线程设置了值，但是在其他线程仍是无法访问，
        //  且在其他线程重写设置了 local 的值并不会影响当前线程的 local
        THREAD_LOCAL.set(10);
        int nThread = 5;
        ExecutorService pool = ExecutorUtils.newFixedThreadPool(nThread);
        for (int i = 0; i < nThread; i++) {
            pool.execute(new ThreadLocalTask(THREAD_LOCAL));
        }
        pool.shutdown();
        if (!pool.awaitTermination(100, TimeUnit.MILLISECONDS)) {
            System.out.println("Force terminate pool.");
            pool.shutdownNow();
        }
        System.out.println(Thread.currentThread().getName() + ": " + THREAD_LOCAL.get());
        THREAD_LOCAL.remove();
    }
}

class ThreadLocalTask implements Runnable {
    private static int counter = 0;
    private final ThreadLocal<Integer> local;

    public ThreadLocalTask(ThreadLocal<Integer> local) {
        this.local = local;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": " + local.get());
        local.set(counter++);
        System.out.println(Thread.currentThread().getName() + ": " + local.get());
        local.remove();
    }
}
