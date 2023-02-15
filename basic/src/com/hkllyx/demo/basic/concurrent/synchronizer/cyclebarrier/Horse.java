package com.hkllyx.demo.basic.concurrent.synchronizer.cyclebarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 模拟马
 *
 * @author HKLLY
 * @date 2019/4/13
 */
public class Horse implements Runnable {
    private static final Random RANDOM = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final CyclicBarrier barrier;
    /** 跑完需要跑的长度 */
    private final int finishLength;
    /** 记录马跑的步数 */
    private int strides = 0;
    /** 排名 */
    private int rank = 0;

    public Horse(CyclicBarrier barrier, int finishLength) {
        this.barrier = barrier;
        this.finishLength = finishLength;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (RANDOM) {
                    if (!hasFinished()) {
                        //  马随机前进几步
                        strides += RANDOM.nextInt(3);
                        strides = Math.min(strides, finishLength);
                    }
                }
                //  CyclicBarrier 等待下一线程执行
                barrier.await();
            }
        } catch (InterruptedException e) {
            System.out.println(this + "Interrupted");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public boolean hasRanked() {
        return rank != 0;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean hasFinished() {
        return strides >= finishLength;
    }

    /**
     * 获取马跑的轨迹
     *
     * @return 轨迹
     */
    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strides; i++) {
            sb.append("*");
        }
        sb.append(this);
        if (rank != 0) {
            sb.append(": Rank ").append(rank);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }
}
