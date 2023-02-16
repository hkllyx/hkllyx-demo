package com.hkllyx.demo.algorithm.sort;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class HeapSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        // 认为arr是一个二叉堆，堆顶是第0个元素
        // 堆结点，假设是第i个元素，它的左子结点是第2 * i + 1个元素，右子结点是第2 * i + 2个元素
        for (int i = arr.length - 1; i > 0; i--) {
            // 构建第0~i（包含）的最大堆。最大堆的特点：一个结点的值比它所有子结点的值都要大
            buildMaxHeap(arr, 0, i);
            // 交换堆顶和第i个元素（最大元素放到最后）
            swap(arr, 0, i);
        }
    }

    private void buildMaxHeap(int[] arr, int vertex, int size) {
        // 计算左右子结点
        int left = (vertex << 1) + 1, right = left + 1;
        int max = vertex;
        if (right <= size) {
            // 分别构建左右子最大堆
            buildMaxHeap(arr, left, size);
            buildMaxHeap(arr, right, size);
            // 如果存在左右子结点的值大于结点，将最大的子结点替换为新的结点
            if (arr[left] >= arr[right] && arr[left] > arr[vertex]) {
                max = left;
            } else if (arr[right] >= arr[left] && arr[right] > arr[vertex]) {
                max = right;
            }
        } else if (left <= size) {
            buildMaxHeap(arr, left, size);
            if (arr[left] > arr[vertex]) {
                max = left;
            }
        }
        if (max != vertex) {
            swap(arr, vertex, max);
        }
    }
}
