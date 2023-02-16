package com.hkllyx.demo.basic.concurrent.executor.forkjointask;

import java.util.concurrent.RecursiveAction;

/**
 * @author HKLLY
 * @date 2019-07-12
 */
public class MergeSortAction extends RecursiveAction {
    private static final int THRESHOLD = 1;
    private final int[] nums;
    private int start;
    private int end;

    public MergeSortAction(int[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            return;
        }
        // 分割任务
        int med = (start + end) >> 1;
        MergeSortAction left = new MergeSortAction(nums, start, med);
        MergeSortAction right = new MergeSortAction(nums, med, end);
        // 执行子任务
        left.fork();
        right.fork();
        // 合并子任务
        left.join();
        right.join();

        // 数组排序、合并
        int[] leftCopy = new int[med - start];
        System.arraycopy(nums, start,
                leftCopy, 0, leftCopy.length);
        int[] rightCopy = new int[end - med];
        System.arraycopy(nums, med,
                rightCopy, 0, rightCopy.length);

        int i = 0, j = 0;
        for (int k = start; k < end; k++) {
            if (i < leftCopy.length && j < rightCopy.length) {
                nums[k] = leftCopy[i] < rightCopy[j]
                        ? leftCopy[i++]
                        : rightCopy[j++];
            } else if (i < leftCopy.length) {
                nums[k] = leftCopy[i++];
            } else {
                nums[k] = rightCopy[j++];
            }
        }
    }
}
