package com.hkllyx.demo.basic.reflect;

import java.lang.reflect.Field;

/**
 * 其他基本类型也类似
 *
 * @author HKLLY
 * @date 2019-08-11
 */
public class FieldDemo {

    public static void main(String[] args) throws ReflectiveOperationException {
        IntHolder ih = new IntHolder();
        Field f = ih.getClass().getDeclaredField("i");

        System.out.println("是否可访问：" + f.isAccessible());
        System.out.println("是否是枚举常量：" + f.isEnumConstant());

        System.out.println("获取修饰符：" + f.getModifiers());
        System.out.println("获取字段的名称：" + f.getName());
        System.out.println("获取类型：" + f.getType());
        System.out.println("获取带参数化类型的类型：" + f.getGenericType());
        System.out.println("获取声明的类：" + f.getDeclaringClass());
        System.out.println("获取字符串形式：" + f.toString());
        System.out.println("获取带参数化类型的字符串形式：" + f.toGenericString());

        // 获取字段值
        f.setAccessible(true);
        // 获取 f 的int 值，条件是 f 的类型必须是 int（不可以是包装类）
        System.out.println(f.get(ih));
        f.set(ih, 2);
        System.out.println(f.get(ih));

        try {
            f.setChar(ih, 'a');
            System.out.println(f.getChar(ih));
        } catch (Exception e) {
            System.out.println("f is not char");
        }
    }
}

