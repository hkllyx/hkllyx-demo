package com.hkllyx.demo.basic.io.io.serializable;

import java.io.Serializable;

/**
 * 可序列化的父类
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class SerializableSupClass implements Serializable {
    private static final long serialVersionUID = -2719500158240772511L;
    private static final String SUP_CONSTANT = "SUP CONSTANT";
    private static String supStaticField = "sup static field";
    private final String supFinalField = "sup final field";
    
    public String supPublicField;
    protected String supProtectedField;
    String supDefaultField;
    
    private int supIntField;
    private String supStringField;
    private transient String supTransientField;
    
    public SerializableSupClass(String supPublicField,
                                String supProtectedField,
                                String supDefaultField,
                                int supIntField,
                                String supStringField,
                                String supTransientField) {
        this.supPublicField = supPublicField;
        this.supProtectedField = supProtectedField;
        this.supDefaultField = supDefaultField;
        this.supIntField = supIntField;
        this.supStringField = supStringField;
        this.supTransientField = supTransientField;
    }

    public static String getSupStaticField() {
        return supStaticField;
    }

    public String getSupFinalField() {
        return supFinalField;
    }

    public String getSupPublicField() {
        return supPublicField;
    }

    public String getSupProtectedField() {
        return supProtectedField;
    }

    public String getSupDefaultField() {
        return supDefaultField;
    }

    public int getSupIntField() {
        return supIntField;
    }

    public String getSupStringField() {
        return supStringField;
    }

    public String getSupTransientField() {
        return supTransientField;
    }

    public void printField() {
        String s = "SerializableSupClass {"
                + "\n    SUP_CONSTANT      = " + SUP_CONSTANT
                + "\n    supStaticField    = " + supStaticField
                + "\n    supFinalField     = " + supFinalField
                + "\n    supPublicField    = " + supPublicField
                + "\n    supProtectedField = " + supProtectedField
                + "\n    subDefaultField   = " + supDefaultField
                + "\n    supIntField       = " + supIntField
                + "\n    supStringField    = " + supStringField
                + "\n    supTransientField = " + supTransientField
                + "\n}";
        System.out.println(s);
    }
}
