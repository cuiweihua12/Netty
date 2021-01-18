package com.cwh.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author cwh
 * @date 2021/1/11 14:02
 */
public class NettyServer {
    @SneakyThrows
    public static void main(String[] args) {

            //bossGroup 只处理连接请求 业务处理交给 workerGroup
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务器启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            //配置参数
            //设置两个线程组
            bootstrap.group(bossGroup,workerGroup)
                    //配置通道为 nio通道
                    .channel(NioServerSocketChannel.class)
                    //设置连接数
                    .option(ChannelOption.SO_BACKLOG,128)
                    //设置保持活动连接
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //初始化通道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            //绑定端口  并且同步端口
            //启动服务
            ChannelFuture future = bootstrap.bind(9999).sync();

            //对关闭通道进行监听 并且同步
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
