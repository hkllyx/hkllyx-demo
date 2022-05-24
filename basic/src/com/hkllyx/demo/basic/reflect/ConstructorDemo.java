package com.hkllyx.demo.basic.reflect;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author HKLLY
 * @date 2019-08-11
 */
public class ConstructorDemo {

    public static void main(String[] args) throws ReflectiveOperationException {
        Constructor<?> c = ArrayList.class.getConstructor(Collection.class);

        System.out.println(c.isVarArgs());
        System.out.println(c.isAccessible());
        System.out.println("输出构造器的字符串形式（不带参数化类型）" + c.toString());
        System.out.println("输出构造器的字符串形式（带参数化类型）" + c.toGenericString());

        System.out.println("获取名称：" + c.getName());
        System.out.println("获取修饰符：" + c.getModifiers());
        System.out.println("获取参数个数：" + c.getParameterCount());
        System.out.println("获取参数列表（参数会带有参数化类型）：" + Arrays.toString(c.getParameters()));
        System.out.println("获取参数化类型：" + Arrays.toString(c.getTypeParameters()));
        System.out.println("获取参数类型：" + Arrays.toString(c.getParameterTypes()));
        System.out.println("获取带参数化类型的参数类型：" + Arrays.toString(c.getGenericParameterTypes()));
        System.out.println("获取带参数化类型的异常类型：" + Arrays.toString(c.getGenericExceptionTypes()));
        System.out.println("获取声明构造器的类：" + c.getDeclaringClass());

        System.out.println("使用构造器生成实例：" + c.newInstance(new ArrayList<>()));
    }
}
