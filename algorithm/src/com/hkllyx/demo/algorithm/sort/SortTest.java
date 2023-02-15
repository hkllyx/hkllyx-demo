package com.hkllyx.demo.algorithm.sort;

import com.hkllyx.demo.algorithm.utils.DataGenerator;
import com.hkllyx.demo.algorithm.utils.StopWatch;

import java.util.Arrays;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class SortTest {
    private final Sortable sortable;
    private final StopWatch stopWatch;

    public static void main(String[] args) {
        SortTest test = new SortTest(new QuickSort());
        // test.debug(10, 10);
        test.run(10, 1000, 200);
    }

    private void debug(int bound, int length) {
        int[] randomIntArray = DataGenerator.randomIntArray(bound, length);
        sortable.sort(randomIntArray);
    }

    private void run(int times, int bound, int length) {
        for (int i = 0; i < times; i++) {
            sortAndCheck(DataGenerator.ascendingIntArray(DataGenerator.randomInt(bound), length));
            sortAndCheck(DataGenerator.descendingIntArray(DataGenerator.randomInt(bound), length));
            sortAndCheck(DataGenerator.randomIntArray(bound, length));
        }
        System.out.println(stopWatch);
    }

    public SortTest(Sortable sortable) {
        this.sortable = sortable;
        this.stopWatch = new StopWatch();
    }

    private void sortAndCheck(int[] arr) {
        String origin = Arrays.toString(arr);

        try {
            stopWatch.start();
            sortable.sort(arr);
            stopWatch.stop();
        } catch (Exception e) {
            System.out.println("Origin: " + origin);
            throw new IllegalStateException("出问题咯！");
        }
        String test = Arrays.toString(arr);

        Arrays.sort(arr);
        String check = Arrays.toString(arr);

        if (!test.equals(check)) {
            System.out.println("Origin: " + origin);
            System.out.println("Result: " + test);
            System.out.println("Check:  " + check);
            throw new IllegalStateException("出问题咯！");
        }
    }
}
