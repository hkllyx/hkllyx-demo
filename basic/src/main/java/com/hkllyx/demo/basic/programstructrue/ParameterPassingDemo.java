package com.hkllyx.demo.basic.programstructrue;

import java.util.Arrays;

/**
 * Java 方法中的传值
 *
 * @author HKLLY
 * @date 2019-08-07
 */
public class ParameterPassingDemo {
    public static void main(String[] args) {
        int i = 0;
        String str = "abc";
        int[] arr = new int[]{0};
        Student stu = new Student("Tom");
        System.out.printf("Before: i = %d, str = %s, arr = %s, stu = %s\n",
                i, str, Arrays.toString(arr), stu.toString());

        //传递的均是值
        change(i, str, arr, stu);

        System.out.printf("After: i = %d, str = %s, arr = %s, stu = %s\n",
                i, str, Arrays.toString(arr), stu.toString());
    }

    static void change(int i, String str, int[] arr, Student stu) {
        // 基本类型参数在当前栈复制，改变不影响原值
        i = 1;
        // 引用类型，引用在当前栈的复制
        // String 的复制使引用指向新的对象，但不影响原应用
        str = "xyz";
        // 数组引用的复制，和原引用指向相同的数组对象
        // 通过当前引用对数组对象的操作会影响原引用指向的对象（修改的就是同一个对象）
        arr[0] = 1;
        // 类似数组
        stu.name = "Jerry";
        System.out.printf("Changing: i = %d, str = %s, arr = %s, stu = %s\n",
                i, str, Arrays.toString(arr), stu.toString());
    }
}

class Student {
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
