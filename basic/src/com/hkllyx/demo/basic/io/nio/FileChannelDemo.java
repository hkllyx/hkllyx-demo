package com.hkllyx.demo.basic.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 1）用于读取，写入，映射和操作文件的通道
 * <p>
 * 2）文件通道是SeekableByteChannel连接到文件的通道。文件本身包含可变长度的字节序列，可以读取和写入，
 * 并且size可以查询其当前值。当字节写入超出其当前大小时，文件的大小会增加; 当文件大小减小时，文件的大小会减小（truncated）。
 * 该文件还可以具有一些关联的元数据，例如访问权限，内容类型和最后修改时间; 此类未定义元数据访问的方法。<br>
 * 方法：position(), position(int), read(ByteBuffer), size(), truncate(long), write(ByteBuffer)
 * <p>
 * 3）GatheringByteChannel接口支持聚集写入，可以将多个ByteBuffer。
 * 写入同一个通道可以使用聚集写入来自动将网络消息的各个部分组装为单个数据流，以便跨越网络传输消息。<br>
 * 方法：write(ByteBuffer[]), write(ByteBuffer[], int, int)
 * <p>
 * 4）ScatteringByteChannel接口支持散射写出，将一个通道的内容写到多个ByteBuffer<br>
 * 方法：read(ByteBuffer[]), read(ByteBuffer, int, int)
 * <p>
 * 5）不可以设为非阻塞
 *
 * @author HKLLY
 * @date 2019-07-19
 */
public class FileChannelDemo {

    public static void main(String[] args) {
        File file = new File("src/main/resources/dir1/demo1.txt");
        try {
            //一）获取FileChannel
            FileOutputStream fos = new FileOutputStream(file);
            //从FileOutputStream获取
            FileChannel fc = fos.getChannel();
            fc.write(ByteBuffer.wrap("Some text".getBytes()));
            //强制Channel将数据写到磁盘中
            fc.force(true);
            //截取FileChannel，文件大小改变
            fc.truncate(4L);
            //关闭通道
            fc.close();
            //从RandomAccessFile获取
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            fc = raf.getChannel();
            //获取通道的大小（对应文件的大小）
            long size = fc.size();
            //将通道的position移到指定位置后写出数据
            fc.position(size).write(ByteBuffer.wrap(" Some more".getBytes()));
            fc.close();
            //从FileInputStream获取
            FileInputStream fis = new FileInputStream(file);
            fc = fis.getChannel();
            //Channel是否打开，如果未打开而进行读写操作会抛出异常
            System.out.println("fc.isOpen() = " + fc.isOpen());
            //重新分配缓冲并将内容读取至缓冲
            ByteBuffer buff = ByteBuffer.allocate(1024);
            fc.read(buff);
            //准备获取缓冲内容（limit = position; position = 0）
            buff.flip();
            //获取缓冲内容
            while (buff.hasRemaining()) {
                System.out.print((char) buff.get());
            }
            System.out.println();
            //将该通道文件的一个区域直接映射（复制）到内存中。
            //第一个参数为映射模式：PRIVATE表示专用，创建缓冲区的副本，与原Buff互不影响；
            // READ_ONLY只读；READ_WRITE读写，对缓冲区的修改可能影响到文件
            //第二个参数为映射开始位置，第三个为需要映射的长度
            ByteBuffer mapBuff = fc.map(FileChannel.MapMode.PRIVATE, 0, fc.size());
            //获取FileChannel的独占锁，获取不到阻塞
            FileLock lock = fc.lock();
            //第一个参数为要锁住部分的开始位置，第二个为要锁住部分的长度，第三个表示是否使用共享锁
            lock = fc.lock(0, fc.size(), true);
            //获取FileChannel的独占锁，获取不到不会阻塞
            lock = fc.tryLock();
            lock = fc.tryLock(0, fc.size(), true);
            fc.close();


            //二）transfer
            File src = new File("README.md");
            File dest = new File("src/main/resources/performance/nio/readme.md");
            //创建读FileChannel
            //创建写FileChannel
            try (FileChannel fcin = new RandomAccessFile(src, "r").getChannel();
                    FileChannel fcout = new RandomAccessFile(dest, "rw").getChannel()) {
                long start = System.currentTimeMillis();
                //将数据发送至fcout
                fcin.transferTo(0, fcin.size(), fcout);
                //从fcin获取数据
                fcout.transferFrom(fcin, 0, fcin.size());

                long duration = System.currentTimeMillis() - start;
                System.out.println("Time: " + duration);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
