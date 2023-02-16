package com.hkllyx.demo.basic.concurrent.synchronizer.cyclebarrier;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xiaoyong3
 * @date 2022/06/22
 */
class Solver {
    private final int n;
    private final float[][] data;
    private final CyclicBarrier barrier;

    public Solver(float[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException();
        }
        data = matrix;
        n = matrix.length;
        //  每个 work 执行完一遍后被阻塞到下一轮，并且在两轮任务之间执行一个任务
        barrier = new CyclicBarrier(n, this::mergeRows);
        for (int i = 0; i < n; ++i) {
            new Thread(new Worker(i)).start();
        }
    }

    public static void main(String[] args) {
        float[][] matrix = new float[5][5];
        Solver solver = new Solver(matrix);
        for (int i = 0; i < matrix.length; i++) {
            new Thread(solver.new Worker(i)).start();
        }
    }

    private void mergeRows() {
        System.out.println("------------------------------");
        for (float[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
    }

    /**
     * 每个 worker 处理一行，当一行处理完成后任务结束
     */
    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        @Override
        public void run() {
            int i = 0;
            while (i < data[myRow].length) {
                data[myRow][i] = 1;
                i++;
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
