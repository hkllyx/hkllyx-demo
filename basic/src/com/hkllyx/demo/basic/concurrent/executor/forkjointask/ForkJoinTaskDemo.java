package com.hkllyx.demo.basic.concurrent.executor.forkjointask;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * 1）Fork/Join框架是Java7提供了的一个用于并行执行任务的框架。<br>
 * 2）是一个把大任务分割成若干个小任务，最终汇总每个小任务结果后得到大任务结果的框架。<br>
 * 3）ForkJoinTask抽象类实现了Future和Serializable接口<br>
 * 4）RecursiveTask继承了ForkJoinTask，有返回值<br>
 * 5）RecursiveAction继承了ForkJoinTask，无返回值<br>
 *
 * @author HKLLY
 * @date 2019-07-12
 */
public class ForkJoinTaskDemo {

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        int[] nums = new int[]{3, 1, 4, 2, 5};

        SumTask task = new SumTask(nums, 0, nums.length);
        pool.execute(task);
        System.out.println(task.get());

        MergeSortAction action =
                new MergeSortAction(nums, 0, nums.length);
        pool.execute(action);
        //  get() 方法保证输出前 action 已完成
        action.get();
        System.out.println(Arrays.toString(nums));

        pool.shutdown();
    }
}
