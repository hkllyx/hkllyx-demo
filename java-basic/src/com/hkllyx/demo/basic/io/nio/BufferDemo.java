package com.hkllyx.demo.basic.io.nio;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

/**
 * 1）定义：buffer（缓冲区）是用于存储基本类型（不包含boolean）的容器（采用数组实现）。<br>
 * 直接继承的类：ByteBuffer,, ShortBuffer CharBuffer, IntBuffer,FloatBuffer, LongBuffer, DoubleBuffer。<br>
 * 缓冲区是特定原始类型的元素的线性有限序列。除了缓冲的内容(content)外，缓冲的本质属性还包括它的
 * 容量(capacity：表示缓冲包含元素的数量，非负且不变)、制(limit：表示第一个不该读/写的元素的位置，非负且小于等于容量)
 * 和位置(position：表示正在读/写的元素的位置，非负且小于限制)
 * <p>
 * 2）数据传输：此类的每个子类定义了get和put操作：<br>
 * 相对操作：从当前位置开始读取或写入一个或多个元素，然后根据传输的元素数量增加该位置。如果请求的传输超过限制，
 * 则相对get操作抛出BufferUnderflowException，相对put操作抛出BufferOverflowException;在这两种情况下，都不传输任何数据。<br>
 * 绝对操作：采用显式元素索引，不影响位置。如果索引参数超过限制，绝对get和put操作将抛出IndexOutOfBoundsException。<br>
 * 当然，数据也可以通过适当通道(Channel)的I/O操作(始终相对于当前位置)在缓冲区中或缓冲区外传输。
 * <p>
 * 3）标记：缓冲区的标记是在调用reset()时将重置其位置的索引。标记并不总是被定义的，但当它被定义时，它永远不会是负的，也永远不会大于位置。
 * 如果标记被定义，那么当位置或限制调整到比标记小的值时，它将被丢弃。如果没有定义标记，则调用reset方法将引发InvalidMarkException。<br>
 * 关系：0 <= mark <= position <= limit <= capacity
 * <p>
 * 4）除了用于访问位置、限制和容量值以及标记和重置的方法之外，该类还定义了以下对缓冲区的操作:<br>
 * clear()：使缓冲区为新的通道读取或相关put操作序列做好准备：limit = capacity； position = 0<br>
 * rewind() 使缓冲区准备好重新读取它已经包含的数据: limit = limit; position = 0<br>
 * flip()：使缓冲区为新的通道写入或相关get操作序列做好准备：limit = position; position = 0<br>
 * 5）只读缓冲：每个缓冲区都是可读的，但不是每个缓冲区都是可写的。每个缓冲区类的突变方法被指定为可选操作，
 * 当在只读缓冲区上调用时，这些操作将抛出ReadOnlyBufferException。
 * 只读缓冲区不允许更改其内容，但其标记、位置和极限值是可变的。
 * 缓冲区是否只读可以通过调用其isReadOnly方法来确定。
 * <p>
 * 6）线程安全：多个并发线程使用缓冲区是不安全的。如果一个缓冲区要被多个线程使用，那么应该通过适当的同步来控制对缓冲区的访问。
 * <p>
 * 7）调用链：没有返回值的方法设置为返回调用他们的buffer（clear/flip/limit/mark/reset）<br>
 * 例：buffer.flip().position(23).limit(42)
 *
 * @author HKLLY
 * @date 2019-07-19
 */
public class BufferDemo {
    
    public static void main(String[] args) {
        /*构造器可见性均为default，不能用构造器创建Buffer*/
        //分配一个ByteBuffer
        ByteBuffer buff = ByteBuffer.allocate(1024);
        //将byte数组包装成ByteBuffer
        buff = ByteBuffer.wrap(new byte[1024]);
        //中偏移和长度
        buff = ByteBuffer.wrap(new byte[1024], 0, 1024);
        
        //Buffer相关方法
        //获取capacity，position，limit
        System.out.println("buff.capacity() = " + buff.capacity());
        System.out.println("buff.position() = " + buff.position());
        System.out.println("buff.limit() = " + buff.limit());
        //= limit - position
        System.out.println("buff.remaining() = " + buff.remaining());
        //= position < limit
        System.out.println("buff.hasRemaining() = " + buff.hasRemaining());
        //是否可读
        System.out.println("buff.isReadOnly() = " + buff.isReadOnly());
        //缓冲的底层数据是否维护在内核缓存中
        System.out.println("buff.isDirect() = " + buff.isDirect());
        //指示此缓冲区是否由可访问数组支持。
        System.out.println("buff.hasArray() = " + buff.hasArray());
    
        //缓冲区的第一个元素在该缓冲区的后备数组中的偏移量(可选操作)。
        System.out.println("buff.arrayOffset() = " + buff.arrayOffset());
        //返回返回此缓冲区的数组(可选操作)。
        System.out.println("buff.array() = " + Arrays.toString(buff.array()));
        
        /*以下方法返回值均为ByteBuffer*/
        //设置position，limit
        buff.position(128);
        buff.limit(512);
        //标记
        buff.mark();
        //position = mark
        buff.reset();
        //使缓冲区为新的通道读取或相关put操作序列做好准备：limit = capacity； position = 0
        buff.rewind();
        //rewind() 使缓冲区准备好重新读取它已经包含的数据: limit = limit; position = 0
        buff.flip();
        //flip()：使缓冲区为新的通道写入或相关get操作序列做好准备：limit = position; position = 0
        buff.clear();
    
        /*以下方法返回值均为ByteBuffer*/
        //在当前位置放入一个byte
        buff.put((byte)1);
        //在当前位置在指定位置放入一个byte
        buff.put(1, (byte)1);
        //在当前位置放入一个byte数组
        buff.put(new byte[5]);
        //在当前位置放入一个byte数组且指定偏移和放入的长度
        buff.put(new byte[5], 0, 5);
        //在当前位置放入一个ByteBuffer
        buff.put(ByteBuffer.allocate(128));
        //在当前位置放入基本类型short（拆分成两个byte）。
        // 同理char, int, float, long, double也有类似方法
        buff.putShort((short)1);
        //在指定位置放入基本类型short。同理char, int, float, long, double也有类似方法
        buff.putShort(1, (short)1);
        
        //获取当前位置的byte
        buff.get();
        //获取指定位置的byte
        buff.get(1);
        //获取当前位置的byte数组
        buff.get(new byte[5]);
        //获取当前位置的byte数组，指定数组的偏移和获取的长度
        buff.get(new byte[5], 0, 5);
        //获取当前位置开始的两个字节，组装成short
        buff.getShort();
        //获取指定位置开始的两个字节，组装成short
        buff.getShort(1);
    
        //创建一个新的字节缓冲区，其内容是此缓冲区内容的共享子序列。
        //新缓冲区的内容将从此缓冲区的当前位置开始。对此缓冲区内容的更改将在新缓冲区中显示，
        // 反之亦然;两个缓冲区的位置，限制和标记值将是独立的。
        //新缓冲区的位置将为零，其容量和限制将是此缓冲区中剩余的字节数，其标记将是未定义的。当
        // 且仅当此缓冲区是直接缓冲区时，新缓冲区将是直接的，并且当且仅当此缓冲区是只读时，它才是只读的。
        buff.slice();
        
        //将转为ShortBuffer，其他基本类型Buffer也能相互转化
        ShortBuffer shortBuff = buff.asShortBuffer();
        
    }
}
