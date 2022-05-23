package com.hkllyx.demo.basic.concurrent.others.performancetuning;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 100000 genes of length 30
 *
 * @author HKLLY
 * @date 2019/4/15
 */
public class FastSimulation {
    /**
     * 基因数
     */
    private static final int N_ELEMENTS = 100000;
    /**
     * 基因长度
     */
    private static final int N_GENES = 30;
    /**
     * 进化线程个数
     */
    private static final int N_EVOLVERS = 50;
    /**
     * 某个基因某个长度上的值
     */
    private static final AtomicInteger[][]
            GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
    private static Random random = new Random(47);

    /**
     * 基因进化线程
     */
    static class Evolver implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                // 随机获取一个基因
                int element = random.nextInt(N_ELEMENTS);
                for (int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if (previous == -1) {
                        previous = N_ELEMENTS - 1;
                    }
                    int next = element + 1;
                    if (next == N_ELEMENTS) {
                        next = 0;
                    }
                    int oldValue = GRID[element][i].get();
                    // 新值等于旧值和前后两个节点的值的平均数（模拟进化）
                    int newValue = (oldValue
                            + GRID[previous][i].get()
                            + GRID[next][i].get()) / 3;
                    // CAS，乐观锁
                    if (!GRID[element][i].compareAndSet(oldValue, newValue)) {
                        System.out.println("Old value changed from " + oldValue);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        // 初始化基因组
        for (int i = 0; i < N_ELEMENTS; i++) {
            for (int j = 0; j < N_GENES; j++) {
                GRID[i][j] = new AtomicInteger(random.nextInt(1000));
            }
        }
        // 执行
        for (int i = 0; i < N_EVOLVERS; i++) {
            pool.execute(new Evolver());
        }
        TimeUnit.SECONDS.sleep(3);
        pool.shutdownNow();
    }
}
