package com.hkllyx.demo.basic.concurrent.others.activeobject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 每个对象自带线程池
 *
 * @author HKLLY
 * @date 2019/4/15
 */
public class ActiveObjectDemo {
    private ExecutorService pool = Executors.newSingleThreadExecutor();
    private Random random = new Random(47);

    public static void main(String[] args) {
        ActiveObjectDemo demo = new ActiveObjectDemo();
        List<Future<?>> results = new CopyOnWriteArrayList<>();
        for (float f = 0; f < 1.0f; f += 0.2) {
            results.add(demo.calculateFloat(f, f));
        }
        for (int i = 0; i < 5; i++) {
            results.add(demo.calculateInt(i, i));
        }
        System.out.println("All asynch calls made");
        while (results.size() > 0) {
            for (Future<?> future : results) {
                if (future.isDone()) {
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    results.remove(future);
                }
            }
            demo.shutdown();
        }
        System.out.println("All done");
    }

    /**
     * 暂停
     */
    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("pause() interrupted");
        }
    }

    /**
     * 提交线程，计算int
     */
    public Future<String> calculateInt(int x, int y) {
        return pool.submit(() -> {
            System.out.println("Starting " + x + " + " + y);
            pause(2000);
            return x + " + " + y + " = " + (x + y);
        });
    }

    /**
     * 提交线程，计算float
     */
    public Future<String> calculateFloat(float x, float y) {
        return pool.submit(() -> {
            System.out.println("Starting " + x + " + " + y);
            pause(2000);
            return x + " + " + y + " = " + (x + y);
        });
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        pool.shutdown();
    }
}
