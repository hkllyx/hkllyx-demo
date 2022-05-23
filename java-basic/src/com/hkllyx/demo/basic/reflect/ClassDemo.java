package com.hkllyx.demo.basic.reflect;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * @author HKLLY
 * @date 2019-08-11
 */
public class ClassDemo {

    public static void main(String[] args) throws ReflectiveOperationException {
        /*--------------------- 获取Class实例 ---------------------*/

        // 3种方式比较
        Class<Vector> c = Vector.class;
        Class<?> c2 = Class.forName("java.util.Vector");
        Class<?> c3 = new Vector<>().getClass();
        System.out.println("c1 == c2: " + (c == c2));
        System.out.println("c1 == c3: " + (c == c3));
        System.out.println("c2 == c3: " + (c2 == c3));

        /*--------------------- 实例相关 ---------------------*/
        System.out.println("使用默认构造器创建实例：" + c.newInstance());
        System.out.println("判断一个对象是否类对象的实例：" + c.isInstance(new Vector<>()));

        List<String> list = new Vector<>();
        list.add("cast");
        // 将一个对象强转成当前 Class 实例对应的类的实例
        Vector vector = c.cast(list);
        System.out.println(vector);
        // 用一个指定类对象将当前类对象强转为以指定类对象为上界的类对象
        Class<? extends Vector> stack = c.asSubclass(Vector.class);
        System.out.println(stack);

        /*--------------------- 获取信息 ---------------------*/

        System.out.println("是否是注解：" + c.isAnnotation());
        System.out.println("是否是数组：" + c.isArray());
        System.out.println("是否是基本类型：" + c.isPrimitive());
        System.out.println("是否是匿名内部类：" + c.isAnonymousClass());
        System.out.println("是否是枚举：" + c.isEnum());
        System.out.println("是否是接口：" + c.isInterface());
        System.out.println("是否是成员内部类：" + c.isMemberClass());
        System.out.println("是否是局部内部类：" + c.isLocalClass());

        System.out.println("获取简单类名：" + c.getSimpleName());
        System.out.println("获取包名 + 简单类名：" + c.getName());
        System.out.println("获取完全限定名：" + c.getCanonicalName());
        System.out.println("获取类型名：" + c.getTypeName());
        System.out.println("获取修饰符类型：" + c.getModifiers());
        System.out.println("获取包信息：" + c.getPackage());

        System.out.println("输出类的字符串形式（不带参数化类型）：" + c.toString());
        System.out.println("输出类的字符串形式（带参数化类型）：" + c.toGenericString());

        /*--------------------- 成员 ---------------------*/

        // 构造器
        System.out.println("指定参数列表获取本类声明的构造器（包括非 public）：" + c.getDeclaredConstructor(int.class));
        System.out.println("指定参数列表获取 public 构造器（包括继承而来的）：" + c.getConstructor(int.class));
        System.out.println("获取本类声明的所有构造器： " + Arrays.toString(c.getDeclaredConstructors()));
        System.out.println("获取本类声明的所有 public 构造器：" + Arrays.toString(c.getConstructors()));
        System.out.println("获取局部/匿名内部类的封闭构造器：" + c.getEnclosingConstructor());

        // Method（方法）有类似方法
        // Field（域）同理，但指定的不是参数列表，而是域的名称（String）

        // 内部类
        System.out.println("获取本类声明的所有内部类（包括非 public的）：" + Arrays.toString(c.getDeclaredClasses()));
        System.out.println("获取 public 内部类（包括继承而来的）：" + c.getDeclaringClass());
        System.out.println("获取局部内部类和匿名内部类：" + c.getEnclosingClass());

        // 枚举常量
        System.out.println("获取枚举常量：" + Arrays.toString(c.getEnumConstants()));

        // 参数化类型
        System.out.println("获取参数化类型（类型擦除，只能获取占位符）：" + Arrays.toString(c.getTypeParameters()));

        /*--------------------- 依赖 ---------------------*/

        //接口
        System.out.println("获取所有接口（包括继承而来的接口）：" + Arrays.toString(c.getInterfaces()));
        System.out.println("获取当前类直接实现且带有参数化类型（类型擦除，只能获取占位符）的接口，："
                + Arrays.toString(c.getGenericInterfaces()));

        //父类
        System.out.println("获取父类" + c.getSuperclass());
        System.out.println("获取带参数化类型（类型擦除，只能获取占位符）的父类：" + c.getGenericSuperclass());

        /*--------------------- 注解 ---------------------*/

        System.out.println("获取接口的注释类型（包括继承而来的接口）：" + Arrays.toString(c.getAnnotatedInterfaces()));
        System.out.println("获取父类的注解类型" + c.getAnnotatedSuperclass());
    }
}