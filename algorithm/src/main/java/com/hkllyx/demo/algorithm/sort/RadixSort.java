package com.hkllyx.demo.algorithm.sort;

import java.util.Arrays;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class RadixSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        // CountingSort中，如果最值相差太大，这样就需要更长的计数数组，造成空间浪费
        // RadixSort（基数计数）就是对它的改善。从低位到高位（个、十、百...），渐进排序，每次计数的值就是个位数

        // 负数转为非负数，暂不考虑溢出
        int padding = 0;
        for (int n : arr) {
            if (n < padding) {
                padding = n;
            }
        }
        if (padding != 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= padding;
            }
        }

        int[] count = new int[10];
        int[] copy = Arrays.copyOf(arr, arr.length);
        for (int div = 1; ; div *= 10) {
            for (int n : arr) {
                // n / div舍去排序过的低位，再% 10获取基数
                count[n / div % 10]++;
            }
            // 全部都是0，不用再排序了
            if (count[0] == arr.length) {
                break;
            }
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            // 计数排序中，出现相同是前移。基数基数排序相同高位时不能影响低位的顺序，所以要反着排序
            for (int i = copy.length - 1; i >= 0; i--) {
                arr[--count[copy[i] / div % 10]] = copy[i];
            }
            // 恢复count和copy
            Arrays.fill(count, 0);
            System.arraycopy(arr, 0, copy, 0, copy.length);
        }

        // 恢复负数
        if (padding != 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += padding;
            }
        }
    }
}
