package com.hkllyx.demo.basic.programstructrue;

/**
 * String 中的 intern() 方法
 *
 * @author HKLLY
 * @date 2019-08-07
 */
public class StringInternDemo {
    public static void main(String[] args) {
        test1();
        test2();
        int[][] d = new int[1][1];
    }

    static void test1() {
        // 在堆中创建了 "1"，也在字符串常量池中创建了 "1"
        String s = new String("1");
        // 返回常量池中 "1" 的地址，但返回值被忽略
        s.intern();
        // s2 指向常量池中的 "1"
        String s2 = "1";
        // false
        System.out.println(s == s2);


        // 运行时通过常量池中的 "1" 连接，在堆中创建了一个 "11"，并不会在常量池中创建 "11"
        String s3 = new String("1") + new String("1");
        // 常量池中没有 "11"。调用 intern() 存储的是堆中 "11" 对象的引用
        // （jdk7 后常量池也在堆中，无需再创建一个 "11" 对象）
        s3.intern();
        // s4 指向常量池中的 "11"（也就是堆中的 "11"）
        String s4 = "11";
        // true
        System.out.println(s3 == s4);
    }

    static void test2() {
        // 在堆中创建了 "2"，也在字符串常量池中创建了 "2"
        String s = new String("2");
        // s2 指向常量池中的 "2"
        String s2 = "2";
        // 返回常量池中 "2" 的地址，但返回值被忽略
        s.intern();
        // false
        System.out.println(s == s2);

        // 运行时通过常量池中的 "1" 连接，在堆中创建了一个 "11"，并不会在常量池中创建 "22"
        String s3 = new String("2") + new String("2");
        // 在常量池中创建 "22"，s4 指向常量池中的 "22"
        String s4 = "22";
        // 返回常量池中 "22" 的地址，但返回值被忽略
        s3.intern();
        // false
        System.out.println(s3 == s4);
    }
}
