package com.hkllyx.demo.basic.io.io.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 可外部化（序列化）的类
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class ExternalizableClass implements Externalizable {
    private static final long serialVersionUID = -4041976707455800481L;
    private static final String CONSTANT = "CONSTANT";
    private static String staticField = "sup static field";
    private final String finalField = "sup final field";
    
    public String publicField;
    protected String protectedField;
    String defaultField;
    
    private int intField;
    private String stringField;
    private transient String transientField;
    
    /**
     * 实现Externalizable接口的类必须需要无参构造器才能实现反序列化
     */
    public ExternalizableClass() {}
    
    public ExternalizableClass(String publicField,
                               String protectedField,
                               String defaultField,
                               int intField,
                               String stringField,
                               String transientField) {
        this.publicField = publicField;
        this.protectedField = protectedField;
        this.defaultField = defaultField;
        this.intField = intField;
        this.stringField = stringField;
        this.transientField = transientField;
    }
    
    /**
     * 自定义序列化的域
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(publicField);
        out.writeObject(protectedField);
        out.writeObject(defaultField);
        out.writeObject(intField);
        out.writeObject(stringField);
        out.writeObject(transientField);
    }
    
    /**
     * 自定义反序列化的域
     */
    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        this.publicField = (String)in.readObject();
        this.protectedField = (String)in.readObject();
        this.defaultField = (String)in.readObject();
        this.intField = (int)in.readObject();
        this.stringField = (String)in.readObject();
        this.transientField = (String)in.readObject();
    }
    
    public void printField() {
        String s = "ExternalizableClass {"
                + "\n    CONSTANT       = " + CONSTANT
                + "\n    staticField    = " + staticField
                + "\n    finalField     = " + finalField
                + "\n    publicField    = " + publicField
                + "\n    protectedField = " + protectedField
                + "\n    defaultField   = " + defaultField
                + "\n    intField       = " + intField
                + "\n    stringField    = " + stringField
                + "\n    transientField = " + transientField
                + "\n}";
        System.out.println(s);
    }
}
