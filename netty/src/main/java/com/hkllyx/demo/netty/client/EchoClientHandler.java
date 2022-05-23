package com.hkllyx.demo.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author xiaoyong3
 * @date 2022/05/20
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 建立连接后调用<br>
     *
     * 一旦建立了连接，就发送消息给服务器
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Netty!", CharsetUtil.UTF_8));
    }

    /**
     * 接受消息后调用<br>
     *
     * 注意，由服务器所发送的消息可以以块的形式被接收<br>
     * 当服务器发送了5个字节，客户端可能接受多次（调用此方法多次）：一次接受2个，一次接受3个
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        // 将消息打印到控制台
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
        // SimpleChannelInboundHandler不需要手动冲刷消息，接受后会自动冲刷
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
