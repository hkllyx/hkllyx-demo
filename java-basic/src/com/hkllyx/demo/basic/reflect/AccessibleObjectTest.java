package com.hkllyx.demo.basic.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 构造器、字段、方法等发射对象的父类
 *
 * @author HKLLY
 * @date 2019-08-11
 */
public class AccessibleObjectTest {

    public static void main(String[] args) throws ReflectiveOperationException {

        /*--------------------- 字段 ---------------------*/
        String s = "chars";
        AccessibleObject obj = String.class.getDeclaredField("hash");

        System.out.println("obj.isAccessible() = " + obj.isAccessible());
        if (!obj.isAccessible()) {
            obj.setAccessible(true);
        }
        ((Field) obj).set(s, -1);
        System.out.println(s.hashCode());


        /*--------------------- 方法 ---------------------*/
        obj = String.class.getMethod("getBytes", int.class, int.class, byte[].class, int.class);

        // 根据获取声明的一个注解
        Deprecated d = obj.getAnnotation(Deprecated.class);
        System.out.println(d);

        // 获取声明的所有注解，返回类型是 Annotation。
        // 可以获取继承而来的注解
        Annotation[] annos = obj.getAnnotations();
        System.out.println(Arrays.toString(annos));
        // 只能获取当前对象声明的注解，忽略继承注解
        annos = obj.getDeclaredAnnotations();
        System.out.println(Arrays.toString(annos));

        // 根据特定注解类型获取所有注解，返回类型为指定类型
        // 可以获取继承而来的注解
        Deprecated[] ds = obj.getAnnotationsByType(Deprecated.class);
        System.out.println(Arrays.toString(ds));
        // 只能获取当前对象声明的注解，忽略继承注解
        ds = obj.getDeclaredAnnotationsByType(Deprecated.class);
        System.out.println(Arrays.toString(ds));

        // 判断对象是否声明了指定注解
        boolean isPresent = obj.isAnnotationPresent(Deprecated.class);
        System.out.println(isPresent);
    }
}
