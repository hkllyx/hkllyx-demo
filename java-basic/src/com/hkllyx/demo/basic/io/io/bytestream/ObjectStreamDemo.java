package com.hkllyx.demo.basic.io.io.bytestream;

import java.io.*;

/**
 * 1）ObjectOutputStream将原始数据类型和Java对象图写入OutputStream。
 * 可以使用ObjectInputStream读取(重新构造)对象。对象的持久存储可以通过使用流的文件来实现。
 * 如果流是网络套接字流，则可以在另一台主机或另一个进程上重新构造对象。<br>
 * 2）只有实现了java.io.Serializable的对象才能被写入输出流<br>
 *
 * @author HKLLY
 * @date 2019-07-17
 */
public class ObjectStreamDemo {
    
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/resources/dir1/demo1.txt");
        out(new FileOutputStream(file));
        in(new FileInputStream(file));
    }
    
    public static void out(OutputStream os) {
        try (ObjectOutputStream out = new ObjectOutputStream(os)) {
            //输出基本类型
            out.writeBoolean(true);
            out.writeByte(Byte.MAX_VALUE);
            out.writeByte(Byte.MIN_VALUE);
            out.writeShort(Short.MAX_VALUE);
            out.writeShort(Short.MIN_VALUE);
            out.writeChar(Character.MAX_VALUE);
            out.writeInt(Integer.MAX_VALUE);
            out.writeLong(Long.MAX_VALUE);
            out.writeFloat(Float.MAX_VALUE);
            out.writeDouble(Double.MAX_VALUE);
            //以bytes和chars的形式输出字符串
            out.writeBytes("bytes");
            out.writeChars("chars");
            //以utf-8的形式输出字符串
            out.writeUTF("utf");
            
            //将当前类的非静态和非瞬态字段写入此流。
            // 这只能从正在序列化的类的writeObject方法中调用。
            // 如果以其他方式调用，它将抛出NotActiveException
            out.defaultWriteObject();
            //将缓冲的Field写入此流
            out.writeFields();
            //将指定的对象写入ObjectOutputStream。
            out.writeObject(os);
            //将“非共享”对象写入ObjectOutputStream。此方法与writeObject相同，
            // 只是它始终将给定对象写为流中的新唯一对象（而不是指向先前序列化实例的反向引用）
            out.writeUnshared(os);
            //指定写入流时要使用的流协议版本。
            //此例程提供了一个钩子，以使当前版本的Serialization
            // 能够以向后兼容以前版本的流格式的格式进行写入。
            out.useProtocolVersion(ObjectStreamConstants.PROTOCOL_VERSION_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void in(InputStream is) {
        try (ObjectInputStream in = new ObjectInputStream(is)) {
            //读取初始类型
            System.out.println(in.readBoolean());
            System.out.println(in.readByte());
            //读取无符号byte（返回值为int类型，值是原值的相反数）
            System.out.println(in.readUnsignedByte());
            System.out.println(in.readShort());
            //读取无符号short（返回值为int类型，值是原值的相反数）
            System.out.println(in.readUnsignedShort());
            System.out.println(in.readChar());
            System.out.println(in.readInt());
            System.out.println(in.readLong());
            System.out.println(in.readFloat());
            System.out.println(in.readDouble());
            //读取以byte数组输出的字符串"bytes"
            byte[] bytes = new byte[5];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = in.readByte();
            }
            System.out.println(new String(bytes));
            //读取以char数组输出的字符串"chars"
            char[] chars = new char[5];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = in.readChar();
            }
            System.out.println(chars);
            //以UTF-8的格式读取当前位置及之后的所有字节
            System.out.println(in.readUTF());
            //跳过多少字节
            in.skipBytes(1);
            //读取字节到缓冲中
            byte[] buff = new byte[100];
            in.readFully(buff, 0, buff.length);
            System.out.println(new String(buff));
            
            //从此流中读取当前类的非静态和非瞬态字段。
            // 这可能只能从被反序列化的类的readObject方法调用。
            // 如果以其他方式调用它，它将抛出NotActiveException。
            in.defaultReadObject();
            //从流中读取持久字段并使其按名称可用。
            in.readFields();
            //从ObjectInputStream中读取一个对象。
            in.readObject();
            //从ObjectInputStream中读取“非共享”对象。此方法与readObject相同，
            // 不同之处在于它阻止对readObject和readUnshared的后续调用
            // 返回对通过此调用获得的反序列化实例的其他引用。
            in.readUnshared();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
