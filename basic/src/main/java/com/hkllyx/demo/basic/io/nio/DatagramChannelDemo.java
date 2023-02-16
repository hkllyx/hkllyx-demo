package com.hkllyx.demo.basic.io.nio;

/**
 * 1）面向数据报的套接字的可选通道。<br>
 * 2）通过调用该类的open()方法创建数据报通道。<br>
 * 3）新创建的数据报通道是打开的，但没有连接。<br>
 * 4）也可以通过调用数据报通道的connect()连接数据报通道，以避免在每次发送和接收操作中执行安全检查的开销。<br>
 * 5）必须连接数据报通道才能使用读和写方法，因为发送接受方法不接受或返回套接字地址。<br>
 *
 * @author HKLLY
 * @date 2019-07-19
 */
public class DatagramChannelDemo {
    /*方法类似SocketChannel，参见SocketChannelDemo*/
}
