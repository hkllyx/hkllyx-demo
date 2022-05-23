package com.hkllyx.demo.basic.jvm.initialization;

/**
 * @author HKLLY
 * @date 2019-08-25
 */
public class B extends A {
    private static final String STATIC_FINAL_FIELD = printField("B: static final");
    private static String staticField = printField("B: static");
    static {
        System.out.println("B: static block");
    }
    private final String finalField = printField("B: final");
    private String normalField = printField("B: normal");
    {
        System.out.println("B: normal block");
    }
    
    public static void main(String[] args) {
        System.out.println("B: main");
    }
    
    private static final String STATIC_FINAL_FIELD_2 = printField("B: static final 2");
    private static String staticField2 = printField("B: static 2");
    static {
        System.out.println("B: static block");
    }
    private final String finalField2 = printField("B: final 2");
    private String normalField2 = printField("B: normal 2");
    {
        System.out.println("B: normal block 2");
    }
    
    public B() {
        System.out.println("B: constructor");
    }
}
