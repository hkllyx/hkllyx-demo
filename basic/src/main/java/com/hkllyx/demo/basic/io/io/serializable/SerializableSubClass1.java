package com.hkllyx.demo.basic.io.io.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerializableSubClass1 extends SerializableSupClass {
    private static final long serialVersionUID = 5414178905623004643L;
    private static final String SUB_CONSTANT = "SUB CONSTANT";
    private static String subStaticField = "sub static field";
    private final String subFinalField = "sub final field";
    
    public String subPublicField;
    protected String subProtectedField;
    String subDefaultField;
    
    private int subIntField;
    private String subStringField;
    private transient String subTransientField;

    public SerializableSubClass1(String supPublicField,
                                 String supProtectedField,
                                 String supDefaultField,
                                 int supIntField,
                                 String supStringField,
                                 String supTransientField,
                                 String subPublicField,
                                 String subProtectedField,
                                 String subDefaultField,
                                 int subIntField,
                                 String subStringField,
                                 String subTransientField) {
        super(supPublicField,
                supProtectedField,
                supDefaultField,
                supIntField,
                supStringField,
                supTransientField);
        this.subPublicField = subPublicField;
        this.subProtectedField = subProtectedField;
        this.subDefaultField = subDefaultField;
        this.subIntField = subIntField;
        this.subStringField = subStringField;
        this.subTransientField = subTransientField;
    }

    public static void setSubStaticField(String subStaticField) {
        SerializableSubClass1.subStaticField = subStaticField;
    }

    public void setSubPublicField(String subPublicField) {
        this.subPublicField = subPublicField;
    }

    public void setSubProtectedField(String subProtectedField) {
        this.subProtectedField = subProtectedField;
    }

    public void setSubDefaultField(String subDefaultField) {
        this.subDefaultField = subDefaultField;
    }

    public void setSubIntField(int subIntField) {
        this.subIntField = subIntField;
    }

    public void setSubStringField(String subStringField) {
        this.subStringField = subStringField;
    }

    public void setSubTransientField(String subTransientField) {
        this.subTransientField = subTransientField;
    }
    
    /**
     * 自定义writeObject方法，序列化输出时会调用此方法
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        //写出非静态和非瞬态域
        oos.defaultWriteObject();
        //自定义写出瞬态域
        oos.writeObject(subTransientField);
    }
    
    /**
     * 自定义readObject方法，序列化时输入会调用此方法
     */
    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        //读取非静态和非瞬态域
        ois.defaultReadObject();
        //自定义读取瞬态域
        this.subTransientField = (String)ois.readObject();
    }
    
    @Override
    public void printField() {
        super.printField();
        String s = "SerializableSubClass1 {"
                + "\n    SUB_CONSTANT      = " + SUB_CONSTANT
                + "\n    subStaticField    = " + subStaticField
                + "\n    subFinalField     = " + subFinalField
                + "\n    subPublicField    = " + subPublicField
                + "\n    subProtectedField = " + subProtectedField
                + "\n    subDefaultField   = " + subDefaultField
                + "\n    subIntField       = " + subIntField
                + "\n    subStringField    = " + subStringField
                + "\n    subTransientField = " + subTransientField
                + "\n}";
        System.out.println(s);
    }
}
