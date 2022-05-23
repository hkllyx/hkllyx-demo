package com.hkllyx.demo.basic.concurrent.thread;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author HKLLY
 * @date 2019-08-21
 */
public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "CallableImpl";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = ExecutorUtils.newSingleThreadExecutor();
        //  返回值使用 Future 接收
        Future<String> future = exec.submit(new CallableDemo());
        System.out.println(future.getClass());
        //  get 方法会阻塞当前线程知道 Callable call 方法返回一个值
        System.out.println(future.get());
        exec.shutdown();
    }
}
