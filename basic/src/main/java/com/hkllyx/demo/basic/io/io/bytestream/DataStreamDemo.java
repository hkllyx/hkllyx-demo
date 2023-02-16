package com.hkllyx.demo.basic.io.io.bytestream;

import java.io.*;

/**
 * 1）允许程序以可移植的方式将Java基本类型数据写入输出流。<br>
 * 2）允许程序可以使用数据输入流将数据读入。<br>
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class DataStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\main\\resources\\dir1\\demo1.txt");
        out(new FileOutputStream(file));
        System.out.println();
        in(new FileInputStream(file));
    }
    
    public static void out(OutputStream os) {
        try (DataOutputStream out = new DataOutputStream(os)) {
            //以基本类型的形式输出
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void in(InputStream is) {
        try (DataInputStream in = new DataInputStream(is)) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
