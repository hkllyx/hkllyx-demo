package com.hkllyx.demo.basic.concurrent.executor.forkjointask;

import java.util.concurrent.RecursiveTask;

/**
 * @author HKLLY
 * @date 2019-07-12
 */
public class SumTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 2533005436686536417L;

    private static final int THRESHOLD = 1;
    private int[] nums;
    private int start;
    private int end;

    public SumTask(int[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum;
        // 如果当前任务达到阈值，则不再分
        if (end - start <= THRESHOLD) {
            sum = nums[start];
        } else {
            // 分割任务
            int mid = (start + end) >> 1;
            SumTask left = new SumTask(nums, start, mid);
            SumTask right = new SumTask(nums, mid, end);
            // 执行子任务
            left.fork();
            right.fork();
            // 合并子任务
            int leftSum = left.join();
            int rightSum = right.join();
            sum = leftSum + rightSum;
        }
        return sum;
    }
}
