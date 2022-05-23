package com.hkllyx.demo.basic.throwable;

import java.util.Arrays;

/**
 * @author HKLLY
 * @date 2019-08-09
 */
public class FinallyAndReturnDemo {
    public static void main(String[] args) {
        System.out.println("test1() 方法返回：" + test1());
        System.out.println("-------------------------------------------");
        System.out.println("test2() 方法返回：" + test2());
        System.out.println("-------------------------------------------");
        System.out.println("test3() 方法返回：" + test3());
        System.out.println("-------------------------------------------");
        System.out.println("test4() 方法返回：" + Arrays.toString(test4()));
        System.out.println("-------------------------------------------");
        System.out.println("test5() 方法返回：" + test5());
        System.out.println("-------------------------------------------");
        System.out.println("test6() 方法返回：" + test6());
    }

    static String test1() {
        try {
            System.out.println("try block");
            return doReturn("try");
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            // 在执行 try 块的 return 语句后，跳出方法之前执行
            System.out.println("finally block");
        }
        return doReturn("outer");
    }

    static String test2() {
        try {
            System.out.println("try block");
            return doReturn("try");
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");
            // 会覆盖 try 块中的返回值
            return doReturn("finally");
        }
    }

    static String test3() {
        String ret;
        try {
            System.out.println("try block");
            ret = doReturn("try");
            return ret;
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");
            // 此时修改不会影响到返回值，说明在执行 return 后返回值不再使用 ret 引用（有另一个引用指向返回值对象）
            ret = "finally block change";
        }
        return doReturn("outer");
    }

    static String[] test4() {
        String[] ret = new String[1];
        try {
            System.out.println("try block");
            ret[0] = doReturn("try");
            return ret;
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");
            // 会影响到返回值
            ret[0] = "finally block change";
        }
        return new String[]{doReturn("outer")};
    }

    static String test5() {
        try {
            System.out.println("try block");
            // 1
            throw new RuntimeException("try block exception");
        } catch (Exception e) {
            // 2
            System.out.println("catch block");
        } finally {
            // 3
            System.out.println("finally block");
        }
        return doReturn("outer");
    }

    static String test6() {
        try {
            System.out.println("try block");
            throw new RuntimeException("try block exception");
        } catch (Exception e) {
            System.out.println("catch block");
            return doReturn("catch");
        } finally {
            // 同 test1
            System.out.println("finally block");
        }
    }

    static String doReturn(String blockName) {
        String ret = blockName + " block return";
        System.out.println("执行的 return：" + ret);
        return ret;
    }
}
