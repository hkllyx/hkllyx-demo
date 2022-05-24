package com.hkllyx.demo.basic.programstructrue;

/**
 * String pool 字符串常量池
 *
 * @author HKLLY
 * @date 2019-08-07
 */
public class StringPoolDemo {
    public static void main(String[] args) {
        String s0 = "hello", s1 = "lo";
        // true。同一个类中的同一个字符串常量是同一个对象
        System.out.println("s0 == \"hello\": " + (s0 == "hello"));
        // true。不同类的同一个字符串常量是同一个对象
        System.out.println("s0 = Other.s0: " + (s0 = Other.s));
        // true。由常量表达式计算出的字符串是在编译时进行计算，然后被当作常量
        System.out.println("s0 == \"hel\" + \"lo\": " + (s0 == "hel" + "lo"));
        // false。在运行时通过连接计算出的字符串是新创建的，因此是不同的
        System.out.println("s0 == \"hel\" + s1: " + (s0 == "hel" + s1));
        // true。显示调用intern()后产生的结果与原来存在的同样内容的字符串常量是一样的。
        System.out.println("s0 == (\"hel\" + s1).intern(): " + (s0 == ("hel" + s1).intern()));

        String s2 = new String("hello");
    }
}

class Other {
    static String s = "hello";
}