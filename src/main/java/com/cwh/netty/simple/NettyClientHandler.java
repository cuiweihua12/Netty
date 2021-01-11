package com.cwh.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author cwh
 * @date 2021/1/11 14:53
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 功能描述: 通道就绪时触发 <br>
     * @Param: [ctx]
     * @Return: void
     * @Date: 2021/1/11 14:53
     **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端： " + ctx.channel().remoteAddress() + " 连接了！");
        ctx.writeAndFlush(Unpooled.copiedBuffer("Open Server Channel Success", CharsetUtil.UTF_8));
    }

    /**
     * 功能描述: 通道断开时触发<br>
     * @Param: [ctx]
     * @Return: void
     * @Date: 2021/1/11 14:58
     **/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端： " + ctx.channel().remoteAddress() + " 断开了！");
    }

    /**
     * 功能描述: 对通道进行读取 <br>
     * @Param: [ctx, msg]
     * @Return: void
     * @Date: 2021/1/11 14:27
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ctx : "+ ctx);
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务端地址：" + ctx.channel().remoteAddress());
        System.out.println("接受到服务端消息：" + buf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 功能描述: 读取完毕后执行 <br>
     * @Param: [ctx]
     * @Return: void
     * @Date: 2021/1/11 14:28
     **/
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //写入并刷新到通道
        //ctx.writeAndFlush(Unpooled.copiedBuffer("receive message to : " + ctx.channel().remoteAddress(),CharsetUtil.UTF_8));
    }

    /**
     * 功能描述: 出现异常时执行<br>
     * @Param: [ctx, cause]
     * @Return: void
     * @Date: 2021/1/11 14:28
     **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


}
