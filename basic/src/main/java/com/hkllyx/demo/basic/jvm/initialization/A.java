package com.hkllyx.demo.basic.jvm.initialization;

/**
 * @author HKLLY
 * @date 2019-08-25
 */
public class A {
    private static final String STATIC_FINAL_FIELD = printField("A: static final");
    private static String staticField = printField("A: static");
    static {
        System.out.println("A: static block");
    }
    
    public static void main(String[] args) {
        System.out.println("A: main");
    }
    
    private final String finalField = printField("A: final");
    private String normalField = printField("A: normal");
    {
        System.out.println("A: normal block");
    }
    
    public A() {
        System.out.println("A: constructor");
    }
    
    public static String printField(String val) {
        System.out.println(val);
        return val;
    }
}
