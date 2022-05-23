package com.hkllyx.demo.basic.io.io.serializable;

import java.io.*;

/**
 * @author HKLLY
 * @date 2019-07-18
 */
public class SerializationDemo {
    public static void main(String[] args) throws Exception {
        //准备文件输入输出字节流
        File file = new File("src/main/resources/dir1/demo1.txt");
        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fis = new FileInputStream(file);
        
        //准备序列化/串行化对象
        SerializableSubClass1 subObj = new SerializableSubClass1(
                "sup public field",
                "sup protected field",
                "sup default field",
                1,
                "sup String field",
                "sup transient field",
                
                "sub public field",
                "sub protected field",
                "sub default field",
                2,
                "sub String field",
                "sub transient field");
        
        //构建对象输入输出字节流
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        /*将指定的对象写入ObjectOutputStream。（序列化）
          对象的类、类的签名、类的非瞬态和非静态字段及其所有序列化父类的值都被写入。
          可以使用writeObject和readObject方法重写该对象类的默认序列化。
          这个对象引用的对象是可传递地编写输出的，因此ObjectInputStream可以重构对象的完整等价图。
          注意：
          1）对象或对象的父类需要实现java.io.Serializable接口
          2）如果对象包含无法序列化的非瞬态或非静态的对象，这个对象也不能被序列化
         */
        oos.writeObject(subObj);
        
        /*从ObjectInputStream中读取一个对象。（反序列化）
          读取对象的类，类的签名，以及类的非瞬态和非静态字段及其所有序列化父类的值。
          可以使用writeObject和readObject方法覆盖类的默认反序列化。
          该对象引用的对象是可传递的，因此readObject可以重建完整的等效对象图。
          注意：
          如果对象的父类没有实现序列化接口（没有被序列化），则会调用器构造函数
         */
        SerializableSubClass1 readObj = (SerializableSubClass1)ois.readObject();
        readObj.printField();
    }
}
