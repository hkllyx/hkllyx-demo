package com.hkllyx.demo.basic.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * 1）继承了AbstractSelectableChannel，可以被Selector操作。<br>
 * 2）可以实现非阻塞连接。<br>
 *
 * @author HKLLY
 * @date 2019-07-19
 */
public class SocketChannelDemo {
    
    public static void main(String[] args) {
        try {
            InetSocketAddress local = new InetSocketAddress("localhost", 8888);
            InetSocketAddress remote = new InetSocketAddress(0);
            Selector selector = Selector.open();
            
            //打开套接字通道并将其连接到远程地址
            SocketChannel sc = SocketChannel.open(remote);
            //打开无连接的套接字通道
            sc = SocketChannel.open();
            //连接远程地址
            sc.connect(remote);
            //绑定本地地址
            sc.bind(local);
            
            //通道是否开启
            System.out.println("sc.isOpen() = " + sc.isOpen());
            //通道是否连接
            System.out.println("sc.isConnected() = " + sc.isConnected());
            //通道上的连接操作是否正在进行
            System.out.println("sc.isConnectionPending() = " + sc.isConnectionPending());
            /*完成连接套接字通道的过程。
              通过将套接字通道置于非阻塞模式然后调用其connect()来启动非阻塞连接操作。一旦建立连接或尝试失败，
                套接字通道将变为可连接，并且可以调用此方法来完成连接序列。如果连接操作失败，则调用此方法将抛出IOException。
              如果此通道已连接，则此方法不会阻塞，并将立即返回true。如果此通道处于非阻塞模式，则如果连接过程尚未完成，则此方法将
                返回false。如果此通道处于阻塞模式，则此方法将阻塞，直到连接完成或失败，并始终返回true或抛出描述失败的已检查异常。
              可以随时调用此方法。如果在调用此方法的同时调用此通道上的读取或写入操作，则该操作将首先阻塞，直到此调用完成。
                如果连接尝试失败，即，如果此方法的调用抛出已检查的异常，则将关闭该通道。
             */
            System.out.println("sc.finishConnect() = " + sc.finishConnect());
            System.out.println();
    
            //获取绑定的本地地址
            System.out.println("sc.getLocalAddress() = " + sc.getLocalAddress());
            //获取连接的远程地址
            System.out.println("sc.getRemoteAddress() = " + sc.getRemoteAddress());
            //关闭输入流，Channel返回值为void的方法返回值为操作之后的本身。
            System.out.println("sc.shutdownInput() = " + sc.shutdownInput());
            //关闭输出流
            System.out.println("sc.shutdownOutput() = " + sc.shutdownOutput());
            //检索与此通道关联的套接字。返回的对象不会声明任何未在Socket类中声明的公共方法。
            System.out.println("sc.socket() = " + sc.socket());
    
            /*SelectableChannel抽象类的方法*/
            //通道上的每个I/O操作是否会阻塞直到完成为止。新创建的通道总是处于阻塞模式。
            System.out.println("sc.isBlocking() = " + sc.isBlocking());
            //通道当前是否已用任何选择器注册。新创建的通道总是为未注册。
            System.out.println("sc.isRegistered() = " + sc.isRegistered());
            //设置通道的阻塞模式
            sc.configureBlocking(false);
            //注册，第一个参数为注册的选择器，第二个是为结果密钥设置的兴趣，
            //  有四类，可读，可写，可连接，可接受（用int表示，可用&来设置多个），
            SelectionKey key = sc.register(selector, SelectionKey.OP_READ);
            //第三个参数是生成密钥的附件; 可以是null
            key = sc.register(selector, SelectionKey.OP_READ, null);
            //通道最后一次使用给定选择器注册时的密钥，如果此通道当前未向该选择器注册，则返回null
            key = sc.keyFor(selector);
            //返回一个操作集，该操作集标识此通道支持的操作。在这个整数值中设置的位表示对这个通道有效的操作。
            // 对于给定的具体通道类，此方法总是返回相同的值。
            int op = sc.validOps();
            /*调整此通道的阻塞模式。
              如果该通道是由一个或多个选择器注册的，那么尝试将其放入阻塞模式将引发IllegalBlockingModeException。
              此方法可以在任何时候调用。新的阻塞模式只会影响在此方法返回后启动的I/O操作。
                对于某些实现，这可能需要阻塞，直到所有挂起的I/O操作完成。
              如果在此方法或寄存器方法的另一个调用正在进行时调用此方法，则它将首先阻塞，直到另一个操作完成。
            */
            Object lock = sc.blockingLock();
            //创建此通道的提供商
            SelectorProvider provider = sc.provider();
    
            //SocketOption相关操作
            sc.supportedOptions();
            sc.setOption(null, null);
            sc.getOption(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
