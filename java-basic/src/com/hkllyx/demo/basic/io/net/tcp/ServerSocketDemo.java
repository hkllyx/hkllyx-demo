package com.hkllyx.demo.basic.io.net.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * 1）该类实现服务器Socket。服务器Socket等待请求通过网络传入。它根据请求执行一些操作，然后可能返回一个结果给请求者。<br>
 * 2）同Socket，ServerSocket的实际工作由SocketImpl类的一个实例执行。 应用程序可以更改创建SocketImpl工厂，
 * 将自己配置为创建适合本地防火墙的ServerSocket。<br>
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class ServerSocketDemo {

    public static void main(String[] args) throws IOException {
        //1）创建一个未绑定的ServerSocket
        ServerSocket server = new ServerSocket();
        System.out.println("new ServerSocket() = " + server);
        //2）创建一个绑定到指定端口的ServerSocket。0表示该端口号自动分配，
        //通常从临时端口范围分配。然后可以通过调用getLocalPort检索这个端口号。
        server = new ServerSocket(8080);
        System.out.println("new ServerSocket(8080) = " + server);
        //3）创建一个绑定到指定端口和指定backlog的ServerSocket。
        //backlog：请求传入连接队列的最大长度表示ServerSocket上请求的最大挂起连接数。
        //它的确切语义是特定于实现的。特别地，实现可能会强制设置最大长度，或者选择忽略参数。
        //所提供的值应该大于0。如果它小于或等于0，那么将使用特定于实现的缺省值（50）。
        server = new ServerSocket(8080, 50);
        System.out.println("new ServerSocket(8080, 50) = " + server);
        //4）创建一个具有指定端口的，backlog和要绑定到的本地IP地址的ServerSocket。
        server = new ServerSocket(8080, 50, InetAddress.getLocalHost());
        System.out.println("new ServerSocket(0, 50, InetAddress.getLocalHost()) = " + server);
        System.out.println();

        //是否绑定
        System.out.println("server.isBound() = " + server.isBound());
        //是否关闭
        System.out.println("server.isClosed() = " + server.isClosed());
        System.out.println();

        //获取指定的IP地址
        System.out.println("server.getInetAddress() = " + server.getInetAddress());
        //获取指定的端口
        System.out.println("server.getLocalPort() = " + server.getLocalPort());
        //获取ServerSocketChannel
        System.out.println("server.getChannel() = " + server.getChannel());
        //获取指定的SocketAddress
        System.out.println("server.getLocalSocketAddress() = " + server.getLocalSocketAddress());
        System.out.println();

        //绑定ip和端口
        server.bind(new InetSocketAddress("localhost", 8080));
        //设置此Socket的性能首选项。第一个参数connectionTime表示短连接时间的相对重要性
        //第二个latency表示短延迟的重要性，第三个表示高带宽的重要性
        server.setPerformancePreferences(10, 10, 10);

        //侦听要连接到此ServerSocket的Socket，并接受它。方法会阻塞直到建立连接为止。返回值为Socket
        System.out.println("server.accept() = " + server.accept());

        //设置java.net.SocketOptions
        server.setReceiveBufferSize(1024);
        server.setReuseAddress(true);
        server.setSoTimeout(1000);

        //查看java.net.SocketOptions中的选项
        System.out.println("server.getReceiveBufferSize() = " + server.getReceiveBufferSize());
        System.out.println("server.getReuseAddress() = " + server.getReuseAddress());
        System.out.println("server.getSoTimeout() = " + server.getSoTimeout());
        System.out.println();
    }
}
