package com.hkllyx.demo.algorithm.sort;

import java.util.Arrays;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class MergeSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 从l和r中间切割成两部分，分别归并排序
        int lr = (l + r) >> 1, rl = lr + 1;
        sort(arr, l, lr);
        sort(arr, rl, r);
        // 合并排序好的两部分
        int[] copy = Arrays.copyOfRange(arr, l, r + 1);
        for (int i = l, d = l; i <= r; i++) {
            if (l <= lr && rl <= r) {
                if (copy[l - d] > copy[rl - d]) {
                    arr[i] = copy[rl++ - d];
                } else {
                    arr[i] = copy[l++ - d];
                }
            } else if (l <= lr) {
                arr[i] = copy[l++ - d];
            } else {
                arr[i] = copy[rl++ - d];
            }
        }
    }
}
