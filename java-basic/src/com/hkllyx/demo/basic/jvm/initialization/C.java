package com.hkllyx.demo.basic.jvm.initialization;

/**
 * @author HKLLY
 * @date 2019-08-25
 */
public class C {
    private static C sc = new C();
    
    static {
        System.out.println("static block");
    }
    
    public static void main(String[] args) {
        System.out.println("main");
    }
    
    {
        System.out.println("normal block");
    }
    
    public C() {
        System.out.println("constructor");
        System.out.println("    STATIC_FINAL_FIELD = " + STATIC_FINAL_FIELD);
        System.out.println("    staticField = " + staticField);
        System.out.println("    finalField = " + finalField);
        System.out.println("    normal = " + normal);
    }
    
    private static final String STATIC_FINAL_FIELD = "static final";
    private static String staticField = "staitc";
    private final String finalField = "final";
    private String normal = "normal";
}
