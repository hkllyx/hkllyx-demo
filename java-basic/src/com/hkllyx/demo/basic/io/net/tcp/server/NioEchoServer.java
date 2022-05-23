package com.hkllyx.demo.basic.io.net.tcp.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author HKLLY
 * @date 2019-07-19
 */
public class NioEchoServer {

    public NioEchoServer(int port) throws IOException {
        init(port);
    }

    public static void main(String[] args) throws IOException {
        new NioEchoServer(9999);
    }

    /**
     * 初始化服务器通道
     *
     * @throws IOException 选择器和通道操作产生的相关异常
     */
    private void init(int port) throws IOException {
        // 初始化选择器、服务器
        try (Selector selector = Selector.open();
                ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            //服务器通道绑定端口
            serverChannel.bind(new InetSocketAddress(port));
            //设为非阻塞
            serverChannel.configureBlocking(false);
            //在服务器注册选择器，key为可接受客户端连接
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Waiting for clients...");
            // 选择器轮询监听，select()方法会处理对应key的时间
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) {
                        //获取客户端通道
                        SocketChannel clientChannel = serverChannel.accept();
                        //设置非阻塞
                        clientChannel.configureBlocking(false);
                        //为客户端注册读事件，准备读取客户端发送的信息
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        //从客户端读取信息
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer revBuff = ByteBuffer.allocate(1024);
                        StringBuilder info = new StringBuilder();
                        while (clientChannel.read(revBuff) != -1) {
                            revBuff.flip();
                            while (revBuff.hasRemaining()) {
                                info.append((char) revBuff.get());
                            }
                            revBuff.clear();
                        }
                        System.out.println(info);
                        //为客户端注册写事件，准备向客户端反馈信息
                        clientChannel.register(selector, SelectionKey.OP_WRITE, info);
                    } else if (key.isWritable()) {
                        //向客户端写入信息
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        clientChannel.write(ByteBuffer.wrap(String.valueOf(key.attachment()).getBytes()));
                        //客户端处理完毕，关闭通道，从选择器中移除通道
                        clientChannel.close();
                        //此key读写事件处理结束，取消
                        key.cancel();
                    }
                    it.remove();
                }
            }
        }
    }
}
