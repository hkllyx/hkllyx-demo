package com.hkllyx.demo.basic.reflect;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author HKLLY
 * @date 2019-08-11
 */
public class ParameterDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        Parameter[] ps = ArrayList.class.getConstructor(Collection.class).getParameters();
        Parameter p = ps[0];

        // 隐式参数指不是方法括号中的参数，而是在方法中调用类的实例域
        System.out.println("参数是否是隐式参数：" + p.isImplicit());
        System.out.println("参数是否具有与类文件对应的名称：" + p.isNamePresent());
        System.out.println("参数是否是变长参数：" + p.isVarArgs());

        System.out.println("获取参数名：" + p.getName());
        System.out.println("获取修饰符（能修饰参数的只有 final）：" + p.getModifiers());
        System.out.println("获取不带参数化类型的类型：" + p.getType());
        System.out.println("获取带有参数化类型的类型：" + p.getParameterizedType());
        System.out.println("获取执行参数的方法或构造器：" + p.getDeclaringExecutable());

        // 参数也是可以被注解的，获取注解的方法和 AccessibleObject 类似
    }
}
