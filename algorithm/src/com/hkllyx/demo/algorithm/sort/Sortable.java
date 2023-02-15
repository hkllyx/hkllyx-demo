package com.hkllyx.demo.algorithm.sort;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public interface Sortable {

    void sort(int[] arr);

    default void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
