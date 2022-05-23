package com.hkllyx.demo.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author xiaoyong3
 * @date 2022/05/20
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new EchoClient("localhost", 8888).start();
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    // 指定使用NIO的传输channel
                    .channel(NioSocketChannel.class)
                    // 远程（服务器）地址
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 添加EchoClientHandler
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            // 同步连接服务器
            ChannelFuture f = bootstrap.connect().sync();
            // 同步关闭channel
            f.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopGroup，释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
