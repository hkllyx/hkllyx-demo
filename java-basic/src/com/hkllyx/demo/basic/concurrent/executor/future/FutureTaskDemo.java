package com.hkllyx.demo.basic.concurrent.executor.future;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * FutureTask实现了RunnableFuture接口
 * RunnableFuture接口继承了Runnable，Future接口
 * FutureTask保存了自身任务执行后的结果
 *
 * @author HKLLY
 * @date 2019-07-11
 */
public class FutureTaskDemo {

    /**
     * 直接接受一个Callable对象
     */

    /**
     * 接受一个Runnable对象和一个结果值
     * 使用Executors.future()方法将其包装成Callable
     */

    public static void main(String[] args) throws Exception {
        // 创建线程池
        ExecutorService exec = ExecutorUtils.newSingleThreadScheduledExecutor();
        // 使用Callable创建FutureTask
        FutureTask<Integer> task = new FutureTask<>(new FibonacciTask(5));
        // 执行任务
        exec.execute(task);
        // 输出task中保存的结果
        System.out.println(task.get());
        System.out.println();

        // 不能使用submit方法执行FutureTask，因为其本质是一个Runnable
        // 执行时Executors.future(Runnable, T)方法会将其包装成结果为null的Callable
        Future<?> future = exec.submit(task);
        System.out.println(future.get());
        System.out.println();

        // 使用Runnable和自定义结果组装成一个Callable
        FutureTask<String> task1 = new FutureTask<>(
                () -> System.out.println("do something"), "TaskResult");
        exec.execute(task1);
        System.out.println(task1.get());

        exec.shutdown();
    }
}
