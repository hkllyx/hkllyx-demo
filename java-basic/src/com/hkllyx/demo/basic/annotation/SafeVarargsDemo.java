package com.hkllyx.demo.basic.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
public class SafeVarargsDemo {

    public static void main(String[] args) {
        // compile parameter: -Xlint:unchecked
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        SafeVarargsDemo.safeMethod1(list);
        SafeVarargsDemo.safeMethod2(list);
        SafeVarargsDemo.unsafeMethod(list);
    }

    public static <T> void safeMethod1(T... args) {
        for (T arg : args) {
            System.out.println(arg);
        }
    }

    @SafeVarargs
    public static <T> void safeMethod2(T... args) {
        for (T arg : args) {
            System.out.println(arg);
        }
    }

    @SafeVarargs
    public static void unsafeMethod(List<Integer>... args) {
        // strList 是一个字符串 List（运行时参数化类型被擦除）
        List<String> strList = Arrays.asList("abc", "xyz");
        // 使用 List 数组指向 args 是可行的
        List[] lists = args;
        // 所以向 List 中放入 strList 也是可行的
        lists[0] = strList;
        // 因为 args 的参数化类型是 Integer，所以用 int 获取内部值是可行的
        // 但是：行时抛出 ClassCastException，因为该元素已经被替代成一个 String 了
        // 所以这个方法的变长参数根本不安全
        int s = args[0].get(0);
    }
}
