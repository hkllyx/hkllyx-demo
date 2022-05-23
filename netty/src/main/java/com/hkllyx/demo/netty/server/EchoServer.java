package com.hkllyx.demo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author xiaoyong3
 * @date 2022/05/20
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        // 启动服务器
        new EchoServer(8888).start();
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    // 指定使用NIO的传输channel
                    .channel(NioServerSocketChannel.class)
                    // 本地地址
                    .localAddress(new InetSocketAddress(port))
                    // 添加EchoServerHandler
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        /** 当新的连接创建，也会初始化一个channel，就会调用此方法 */
                        @Override
                        public void initChannel(SocketChannel ch) {
                            // 将EchoServerHandler添加到chanel的pipline中
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            // 绑定地址。sync表示同步等待服务器关闭
            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println(EchoServer.class.getName()
                    + " started and listen on " + channelFuture.channel().localAddress());
            // 同步关闭channel
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 同步关闭EventLoopGroup，释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
