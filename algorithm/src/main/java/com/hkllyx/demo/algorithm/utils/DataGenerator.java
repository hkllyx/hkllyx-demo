package com.hkllyx.demo.algorithm.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author xiaoyong3
 * @date 2023/02/15
 */
public class DataGenerator {
    private static final Random RANDOM = new SecureRandom();

    private DataGenerator() {}

    public static int randomInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static int[] randomIntArray(int bound, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = randomInt(bound);
        }
        return arr;
    }

    public static int[] ascendingIntArray(int start, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = start + i;
        }
        return arr;
    }

    public static int[] descendingIntArray(int start, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = start - i;
        }
        return arr;
    }
}
