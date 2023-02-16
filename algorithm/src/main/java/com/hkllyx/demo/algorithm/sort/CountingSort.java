package com.hkllyx.demo.algorithm.sort;

import java.util.Arrays;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class CountingSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        // 找到最值
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n : arr) {
            if (n < min) {
                min = n;
            }
            if (n > max) {
                max = n;
            }
        }
        // 创建数组
        int length = max - min + 1;
        int[] count = new int[length];
        for (int n : arr) {
            // 用数组下标计数
            count[n - min]++;
        }
        // count的元素值累加其之前所有值之和，这样就可以知道每个下标对应的arr元素的位置
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        // 确定位置后，填充到arr
        int[] copy = Arrays.copyOf(arr, arr.length);
        for (int n : copy) {
            // count[n - min]则可以确定n的位置，但计数从1开始，数组下标从0开始，所以要减去1
            // 计数使用后减去1，再次出现n的时候，它的位置要前移一位
            arr[--count[n - min]] = n;
        }
    }
}
