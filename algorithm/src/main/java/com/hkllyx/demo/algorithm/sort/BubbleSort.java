package com.hkllyx.demo.algorithm.sort;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class BubbleSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 循环之后，当前最大的数放在倒到数第i个
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 相邻两个数比较
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
}
