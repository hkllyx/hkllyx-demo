package com.hkllyx.demo.basic.jvm;

/**
 * @author HKLLY
 * @date 2019-08-14
 */
public class NotInitializationDemo {
    public static void main(String[] args) {
        System.out.println(SubClass.value);

        SuperClass[] superClasses = new SuperClass[10];
        System.out.println(superClasses.getClass());
    
        System.out.println(ConstClass.HELLO_BINGO);
    }
}

class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}

class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }
    public static final String HELLO_BINGO = "Hello Bingo";
}
