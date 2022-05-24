package com.hkllyx.demo.basic.io.io.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 1）此类的实例支持读取和写入随机访问文件。随机访问文件的行为类似于存储在文件系统中的大型字节数组。<br>
 * 2）有一种游标或索引到隐含数组中，称为文件指针（file pointer）。<br>
 * 3）Input操作从文件指针开始读取字节，并使推进文件指针，超过读取的位置。 如果以读/写模式创建随机访问文件，则输出操作也可用改指针。<br>
 * 4）Output操作从文件指针开始写入字节，并使推进文件指针，超过写入的位置。 写入隐含数组当前末尾的输出操作会导致数组被扩展。<br>
 * 5）文件指针可以由getFilePointer()读取并由seek()设置 。<br>
 * 6）通常，对于此类中的所有读取例程，如果在读取了所需的字节数之前达到文件结尾， EOFException（一种IOException则抛出）。
 * 如果由于文件结束之外的任何原因无法读取任何字节， 则抛出IOException而不是EOFException。
 * 特别是，如果流已经关闭，则可能抛出一个IOException。<br>
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class RandomAccessFileDemo {
    
    public static void main(String[] args) {
        File file = new File("src/main/resources/dir1/demo1.txt");
        /*模式：
          “r”   仅读。调用任何写入方法都将抛出IOException。
          “rw”  读写。如果该文件尚不存在，则将尝试创建该文件。
          “rws” 读写，与“rw”一样，并且还要求将文件内容或元数据的更新同步写入底层存储设备。
          “rwd” 读写，与“rw”一样，并且还要求将文件内容的更新同步写入底层存储设备。
        */
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            //获取FileDescriptor对象
            System.out.println("raf.getFD() = " + raf.getFD());
            //获取Channel对象
            System.out.println("raf.getChannel() = " + raf.getChannel());
    
            //获取文件指针位置
            System.out.println("raf.getFilePointer() = " + raf.getFilePointer());
            //写基本类型
            raf.writeBoolean(true);
            raf.writeByte(Byte.MAX_VALUE);
            raf.writeByte(Byte.MIN_VALUE);
            raf.writeShort(Short.MAX_VALUE);
            raf.writeShort(Short.MIN_VALUE);
            raf.writeChar(Character.MAX_VALUE);
            raf.writeInt(Integer.MAX_VALUE);
            raf.writeLong(Long.MAX_VALUE);
            raf.writeFloat(Float.MAX_VALUE);
            raf.writeDouble(Double.MAX_VALUE);
            //以bytes和chars的形式写字符串
            raf.writeBytes("bytes");
            raf.writeChars("chars");
            //以utf-8的形式输出字符串
            raf.writeUTF("utf");
            //获取长度（文件字节数）
            System.out.println("raf.length() = " + raf.length());
            //再次获取文件指针位置
            System.out.println("raf.getFilePointer() = " + raf.getFilePointer());
            
            //将文件指针移到文件开始位置
            raf.seek(0L);
            //读取初始类型
            System.out.println(raf.readBoolean());
            System.out.println(raf.readByte());
            //读取无符号byte（返回值为int类型，值是原值的相反数）
            System.out.println(raf.readUnsignedByte());
            System.out.println(raf.readShort());
            //读取无符号short（返回值为int类型，值是原值的相反数）
            System.out.println(raf.readUnsignedShort());
            System.out.println(raf.readChar());
            System.out.println(raf.readInt());
            System.out.println(raf.readLong());
            System.out.println(raf.readFloat());
            System.out.println(raf.readDouble());
            //读取以byte数组输出的字符串"bytes"
            byte[] bytes = new byte[5];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = raf.readByte();
            }
            System.out.println(new String(bytes));
            //读取以char数组输出的字符串"chars"
            char[] chars = new char[5];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = raf.readChar();
            }
            System.out.println(chars);
            //以UTF-8的格式读取当前位置及之后的所有字节
            System.out.println(raf.readUTF());
            
            raf.seek(0);
            raf.skipBytes(1);
            bytes = new byte[(int)(raf.length() - raf.getFilePointer())];
            raf.readFully(bytes);
            System.out.println(Arrays.toString(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
