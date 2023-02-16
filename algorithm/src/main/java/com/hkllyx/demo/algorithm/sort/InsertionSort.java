package com.hkllyx.demo.algorithm.sort;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class InsertionSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        // 认为第0个是插好了的，从第1个开始插入
        for (int i = 1; i < arr.length; i++) {
            // 用tmp记住待插入元素的值，后续元素后移可能被覆盖
            int tbi = arr[i];
            // 如果第j - 1位大于待插入元素，则后移一位
            int j = i;
            for (; j > 0 && arr[j - 1] > tbi; j--) {
                arr[j] = arr[j - 1];
            }
            // 插入元素
            if (i != j) {
                arr[j] = tbi;
            }
        }
    }
}
