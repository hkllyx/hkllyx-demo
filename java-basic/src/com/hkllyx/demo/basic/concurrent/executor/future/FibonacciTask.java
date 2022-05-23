package com.hkllyx.demo.basic.concurrent.executor.future;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author HKLLY
 * @date 2019/4/9
 */
public class FibonacciTask implements Callable<Integer> {
    private int i;

    public FibonacciTask(int i) {
        this.i = i;
    }

    public static int fib(int i) {
        if (i < 2) {
            return 1;
        }
        return fib(i - 1) + fib(i - 2);
    }

    @Override
    public Integer call() {
        return fib(i);
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new FibonacciTask(i)));
        }
        for (Future<Integer> f : results) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
        }
    }
}
