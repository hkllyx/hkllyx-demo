package com.hkllyx.demo.basic.annotation;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
@MyAnnotation("class")
public class AnnotationInvocationHandlerDemo {

    public static void main(String[] args) {
        Class<AnnotationInvocationHandlerDemo> clazz = AnnotationInvocationHandlerDemo.class;
        MyAnnotation hello = clazz.getAnnotation(MyAnnotation.class);
        System.out.println(hello);
    }
}