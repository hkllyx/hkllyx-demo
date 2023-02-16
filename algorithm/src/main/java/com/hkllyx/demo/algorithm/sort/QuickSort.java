package com.hkllyx.demo.algorithm.sort;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class QuickSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 找到一个轴心点，它左侧的元素都比它小，右侧的元素都比它大
        int pivot = partition(arr, l, r);
        // 递归排序轴心点两侧
        sort(arr, l, pivot - 1);
        sort(arr, pivot + 1, r);
    }

    private int partition(int[] arr, int l, int r) {
        // 选择任一个元素作为轴心点，这里选择最后一个，第r位
        int i = l;
        for (int j = l; j < r; j++) {
            // 如果第j位元素大于轴心点元素，i不需要移动，让它指向第一个大于轴心点的元素
            // 如果第j位元素小于轴心点元素
            if (arr[j] < arr[r]) {
                // i等于j，说明没有遍历到大于轴心点的元素
                if (i != j) {
                    // 交换位置，让小于轴心点的元素在前
                    swap(arr, i, j);
                }
                i++;
            }
        }
        if (i != r) {
            swap(arr, i, r);
        }
        return i;
    }
}
