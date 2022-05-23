package com.hkllyx.demo.basic.io.net.tcp;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

/**
 * 1）Socket实现了客户端Socket（也称为“sockets”）。Socket是两台机器之间通信的端点。<br>
 * 2）Socket的实际工作由SocketImpl（抽象类）的一个实例执行。通过更改创建SocketImpl的Socket工厂，
 * 应用程序可以将自己配置为创建适合本地防火墙的Socket。<br>
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class SocketDemo {

    public static void main(String[] args) throws Exception {
        InetAddress ipAddr = InetAddress.getLocalHost();
        String host = ipAddr.getHostName();
        
        //1）创建一个未连接的，使用系统默认的SocketImpl的Socket。
        Socket client = new Socket();
        System.out.println("new Socket() = " + client);
        //2）创建一个Socket，并将其连接到指定主机上的指定端口号
        client = new Socket(host, 8080);
        System.out.println("Socket(host, 8080) = " + client);
        //3）创建一个Socket，并将其连接到指定ip地址上的指定端口号
        client = new Socket(ipAddr, 8080);
        System.out.println("new Socket(ipAddr, 8080) = " + client);
        //4）创建Socket，并将其连接到指定远程端口上的指定远程主机。Socket还将绑定提供的本地地址和端口。
        client = new Socket(host, 8080, ipAddr, 8080);
        System.out.println("new Socket(host, 8080, ipAddr, 8080) = " + client);
        //5）创建一个未连接的Socket，指定应该使用的代理类型(如果有的话)，而不管其他设置如何。
        client = new Socket(Proxy.NO_PROXY);
        System.out.println("new Socket(Proxy.NO_PROXY) = " + client);
        System.out.println();
    
        //Socket是否绑定
        System.out.println("client.isBound() = " + client.isBound());
        //Socket是否关闭
        System.out.println("client.isClosed() = " + client.isClosed());
        //Socket是否连接
        System.out.println("client.isConnected() = " + client.isConnected());
        //Socket是否关闭了输入流
        System.out.println("client.isInputShutdown() = " + client.isInputShutdown());
        //Socket是否关闭了输出流
        System.out.println("client.isOutputShutdown() = " + client.isOutputShutdown());
        System.out.println();
        
        //将Socket绑定到本地InetSocketAddress
        client.bind(new InetSocketAddress("localhost", 8080));
        //连接服务器InetSocketAddress
        client.connect(new InetSocketAddress("localhost", 8080));
        //在超时时间内连接服务器InetSocketAddress
        client.connect(new InetSocketAddress("localhost", 8080), 1000);
        //设置此Socket的性能首选项。第一个参数connectionTime表示短连接时间的相对重要性
        //第二个latency表示短延迟的重要性，第三个表示高带宽的重要性
        client.setPerformancePreferences(10, 10, 10);
    
        //获取ip地址
        System.out.println("client.getInetAddress() = " + client.getInetAddress());
        //获取端口号
        System.out.println("client.getPort() = " + client.getPort());
        //获取本地端口号
        System.out.println("client.getLocalPort() = " + client.getLocalPort());
        //获取SocketChannel
        System.out.println("client.getChannel() = " + client.getChannel());
        //获取字节输入流
        System.out.println("client.getInputStream() = " + client.getInputStream());
        //获取字节输出流
        System.out.println("client.getOutputStream() = " + client.getOutputStream());
        //获取本地SocketAddress
        System.out.println("client.getLocalSocketAddress() = " + client.getLocalSocketAddress());
        //获取远程SocketAddress
        System.out.println("client.getRemoteSocketAddress() = " + client.getRemoteSocketAddress());
        //关闭字节输入流
        client.shutdownInput();
        //关闭字节输出流
        client.shutdownOutput();
        //关闭Socket
        client.close();
        System.out.println();

        //设置java.net.SocketOptions
        client.setKeepAlive(true);
        client.setOOBInline(true);
        client.setReuseAddress(true);
        client.setTcpNoDelay(true);
        client.setReceiveBufferSize(1024);
        client.setSendBufferSize(1024);
        client.setSoLinger(true, 1000);
        client.setSoTimeout(1000);
        client.setTrafficClass(0);
        
        //查看java.net.SocketOptions中的选项
        System.out.println("client.getKeepAlive() = " + client.getKeepAlive());
        System.out.println("client.getOOBInline() = " + client.getOOBInline());
        System.out.println("client.getReuseAddress() = " + client.getReuseAddress());
        System.out.println("client.getTcpNoDelay() = " + client.getTcpNoDelay());
        System.out.println("client.getReceiveBufferSize() = " + client.getReceiveBufferSize());
        System.out.println("client.getSendBufferSize() = " + client.getSendBufferSize());
        System.out.println("client.getSoLinger() = " + client.getSoLinger());
        System.out.println("client.getSoTimeout() = " + client.getSoTimeout());
        System.out.println("client.getTrafficClass() = " + client.getTrafficClass());
        System.out.println();
    }
}
