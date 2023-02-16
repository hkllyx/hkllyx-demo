package com.hkllyx.demo.basic.reflect;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author HKLLY
 * @date 2019-08-11
 */
public class ArrayDemo {

    public static void main(String[] args) {
        /*--------------------- 一维数组 ---------------------*/
        // 注意，返回值为 Object 类型，而不是 Array 类型
        Object obj = Array.newInstance(int.class, 5);
        System.out.println("查看生成的类：" + obj.getClass());
        int length = Array.getLength(obj);
        System.out.println("获取长度：" + length);
        for (int i = 0; i < length; i++) {
            // 基本类型方法赋值：只能用于基本类型。
            // 基本类型都实现了类似方法
            Array.setInt(obj, i, i);
            // 基本类型方法数组元素：只能用于基本类型。
            // 基本类型都实现了类似方法
            int tmp = Array.getInt(obj, i);

            // 通用方法赋值：也可用于引用类型
            Array.set(obj, i, i + tmp);
            // 通用方法获取数组元素：也可用于引用类型
            System.out.println(Array.get(obj, i));
        }

        /*--------------------- 多维数组 ---------------------*/
        Object outer = Array.newInstance(int.class, 5, 5, 5);
        System.out.println("查看生成的类：" + outer.getClass());
        length = Array.getLength(outer);
        System.out.println("获取长度：" + length);
        int c = 1;
        for (int i = 0; i < length; i++) {
            Object middle = Array.get(outer, i);
            for (int j = 0; j < Array.getLength(middle); j++) {
                Object inner = Array.get(middle, j);
                for (int k = 0; k < Array.getLength(inner); k++) {
                    Array.setInt(inner, k, c++);
                }
            }
        }
        System.out.println(Arrays.deepToString(new Object[]{outer}));
    }
}
