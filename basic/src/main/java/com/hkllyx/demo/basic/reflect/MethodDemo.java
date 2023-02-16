package com.hkllyx.demo.basic.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author HKLLY
 * @date 2019-08-11
 */
public class MethodDemo {

    public static void main(String[] args) throws ReflectiveOperationException {
        IntHolder ih = new IntHolder();
        ;
        Method m = ih.getClass().getDeclaredMethod("setInt", Integer.class);

        System.out.println("参数是否是变长参数：" + m.isVarArgs());
        // 默认方法是解控中带有 default 关键字的方法
        System.out.println("是否是默认方法：" + m.isDefault());
        System.out.println("是否可访问：" + m.isAccessible());

        System.out.println("输出构造器的字符串形式（不带参数化类型）" + m.toString());
        System.out.println("输出构造器的字符串形式（带参数化类型）" + m.toGenericString());

        System.out.println("获取声明方法的类：" + m.getDeclaringClass());
        System.out.println("获取名称：" + m.getName());
        System.out.println("获取修饰符：" + m.getModifiers());
        System.out.println("获取参数个数：" + m.getParameterCount());
        System.out.println("获取参数列表（参数会带有参数化类型）：" + Arrays.toString(m.getParameters()));
        System.out.println("获取参数化类型：" + Arrays.toString(m.getTypeParameters()));
        System.out.println("获取参数类型：" + Arrays.toString(m.getParameterTypes()));
        System.out.println("获取带参数化类型的参数类型：" + Arrays.toString(m.getGenericParameterTypes()));
        System.out.println("获取带参数化类型的异常类型：" + Arrays.toString(m.getGenericExceptionTypes()));
        System.out.println("获取返回值类型：" + m.getGenericReturnType());
        // 如果默认值是基本类型，返回的是其包装类
        System.out.println("获取注解的默认值：" + m.getDefaultValue());

        // 掉用方法
        m.setAccessible(true);
        System.out.println(ih);
        m.invoke(ih, 100);
        System.out.println(ih);
    }
}
