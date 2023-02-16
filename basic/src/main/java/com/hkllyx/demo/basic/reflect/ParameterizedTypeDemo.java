package com.hkllyx.demo.basic.reflect;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author HKLLY
 * @date 2019-08-11
 */
public class ParameterizedTypeDemo {

    public static void main(String[] args) {
        ParameterizedType pt = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println("ParameterizedType 带有参数化类型：" + pt);
        System.out.println("获取占位符：" + Arrays.toString(pt.getActualTypeArguments()));
        System.out.println("获取声明了当前参数化类型的类型（不带参数化类型）：" + pt.getRawType());
        //OwnerType：如果类型为 O<T>.I<S>，则返回 O<T>
        System.out.println("获取该参数表示的类型：" + pt.getOwnerType());
    }
}
