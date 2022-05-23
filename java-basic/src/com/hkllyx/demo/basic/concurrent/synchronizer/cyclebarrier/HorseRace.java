package com.hkllyx.demo.basic.concurrent.synchronizer.cyclebarrier;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HKLLY
 * @date 2019/4/13
 */
public class HorseRace {
    private static final int FINISH_NUM = 75;
    private static final String SIDE_LINE;

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FINISH_NUM; i++) {
            sb.append("=");
        }
        SIDE_LINE = sb.toString();
    }

    private List<Horse> horses;
    private ExecutorService pool;

    public HorseRace(int nHorses, int pause) {
        horses = new ArrayList<>();
        pool = ExecutorUtils.newFixedThreadPool(nHorses);
        //  创建有 nHorses 部分的 CyclicBarrier。
        AtomicInteger rank = new AtomicInteger();
        CyclicBarrier barrier = new CyclicBarrier(nHorses, () -> {
            System.out.println(SIDE_LINE);
            for (Horse horse : horses) {
                if (horse.hasFinished() && !horse.hasRanked()) {
                    horse.setRank(rank.incrementAndGet());
                }
                System.out.println(horse.tracks());
            }
            if (rank.get() == nHorses) {
                pool.shutdownNow();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                System.out.println("Race Completed");
            }
        });
        // 添加带有CycleBarrier的线程（马）
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier, FINISH_NUM);
            horses.add(horse);
            pool.execute(horse);
        }
    }

    public static void main(String[] args) {
        new HorseRace(5, 200);
    }
}
