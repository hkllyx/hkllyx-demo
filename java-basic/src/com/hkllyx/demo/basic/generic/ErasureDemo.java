package com.hkllyx.demo.basic.generic;

import java.util.*;

/**
 * @author HKLLY
 * @date 2019-08-09
 */
public class ErasureDemo {

    public static void main(String[] args) {
        test1();
        test2();
        test3(new SupClass());
    }

    static void test1() {
        Class<?> c1 = new ArrayList<String>().getClass();
        Class<?> c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1.equals(c2));
    }

    static void test2() {
        List<Integer> list = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
    }

    static <T extends SupClass> void test3(T t) {
        t.f();
        //t.f2();// 擦除到 SupClass，该类不存在 f2()
    }
}

class SupClass {
    void f() {
        System.out.println("SupClass");
    }
}

class SubClass extends SupClass {
    void f2() {
        System.out.println("SubClass");
    }
}
