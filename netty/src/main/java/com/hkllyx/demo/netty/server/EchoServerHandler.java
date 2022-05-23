package com.hkllyx.demo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author xiaoyong3
 * @date 2022/05/20
 */
@ChannelHandler.Sharable // 表示实例可以共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /** 收到消息后调用<br> */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 将输入消息打印到控制台
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        // 将所接收的消息返回给发送者
        // 注意，这还没有冲刷数据，因为write是异步的，不能保证信息已经写完
        ctx.write(in);
    }

    /** 最后一次收到消息后调用 */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        // 消息读取结束，冲刷消息到客户端
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                // 关闭通道后
                .addListener(ChannelFutureListener.CLOSE);
    }

    /** 读取消息错误后调用，处理异常 */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 打印异常堆栈，然后关闭通道
        cause.printStackTrace();
        ctx.close();
    }
}
