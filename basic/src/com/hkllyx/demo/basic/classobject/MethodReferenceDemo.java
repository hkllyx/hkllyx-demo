package com.hkllyx.demo.basic.classobject;

import java.util.Arrays;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
public class MethodReferenceDemo {

    public static void main(String[] args) {
        test2();
        test3();
    }

    static void test3() {
        String[] strs = new String[]{"abc", "xyz", "ACD", "efg"};
        // Class::instanceMethod
        // String::compareToIgnoreCase 等价于
        // (x, y) -> x.compareToIgnoreCase(y) 等价于
        // new Comparator<String>() {
        //     @Override
        //     public int compare(String x, String y) {
        //         return x.compareToIgnoreCase(y);
        //     }
        // }
        Arrays.sort(strs, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(strs));
    }

    static void test2() {
        Integer[] ints = new Integer[]{1, 3, 5, 4, 2};
        // Class::staticMethod
        // Math::max 等价于
        // (x, y) -> Math.max(x, y) 等价于
        // new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer x, Integer y) {
        //         return Math.max(x, y)
        //     }
        // }
        Arrays.sort(ints, Math::max);
        System.out.println(Arrays.toString(ints));
    }
}
