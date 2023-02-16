package com.hkllyx.demo.algorithm.sort;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class SelectionSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 选择一个数认为是最小的（这里选第i个），和之后的所有数比较
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            // 将第min位的数移到第i位
            if (i != min) {
                swap(arr, i, min);
            }
        }
    }
}
