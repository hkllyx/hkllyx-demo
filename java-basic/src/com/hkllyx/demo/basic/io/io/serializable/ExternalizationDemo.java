package com.hkllyx.demo.basic.io.io.serializable;

import java.io.*;

/**
 * 
 * @author HKLLY
 * @date 2019-07-18
 */
public class ExternalizationDemo {
    public static void main(String[] args) throws Exception {
        //准备文件输入输出字节流
        File file = new File("src/main/resources/dir1/demo1.txt");
        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fis = new FileInputStream(file);
        
        //准备序列化/串行化对象
        ExternalizableClass subObj = new ExternalizableClass(
                "public field",
                "protected field",
                "default field",
                1,
                "String field",
                "transient field");
        
        //构建对象输入输出字节流
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        //序列化
        oos.writeObject(subObj);
        
        //反序列化，需要无参构造器（反序列化时，先用无参构造器创建对象，在对域赋值）
        ExternalizableClass readObj = (ExternalizableClass)ois.readObject();
        readObj.printField();
    }
}
