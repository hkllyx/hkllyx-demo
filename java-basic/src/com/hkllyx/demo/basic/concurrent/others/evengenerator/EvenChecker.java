package com.hkllyx.demo.basic.concurrent.others.evengenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 检查是否为偶数
 *
 * @author HKLLY
 * @date 2019/4/11
 */
public class EvenChecker implements Runnable {
    private final int id;
    private IntGenerator generator;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    /**
     * 执行测试
     *
     * @param g     int生成器
     * @param count 线程数
     */
    public static void test(IntGenerator g, int count) {
        System.out.println("Test Started.");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(g, i));
        }
        exec.shutdown();
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int value = generator.next();
            // 如果不是偶数，停止生成
            if (value % 2 != 0) {
                System.out.println("Thread" + id + ": " + value + " NOT evengenerator!");
                generator.cancel();
            } else {
                System.out.println("Thread" + id + ": " + value);
            }
        }
    }
}
