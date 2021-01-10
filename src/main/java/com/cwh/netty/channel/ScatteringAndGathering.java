package com.cwh.netty.channel;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @ClassName : ScatteringAndGathering
 * @Description :  Scattering  将数据写书buffer可以是使用buffer数组，依次写入 [分散]
 *                 Gathering   从buffer中读取数据，可以采用buffer数组，依次读取 [聚合]
 * @Author : cuiweihua
 * @Date: 2021-01-10 13:16
 */
public class ScatteringAndGathering {
    @SneakyThrows
    public static void main(String[] args) {
        //使用serverSocketChanner 和 SocketChannel网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        //绑定端口到socket并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        int messageLength = 8;

        //等待客户端链接
        SocketChannel socketChannel = serverSocketChannel.accept();

        //循环读取
        while (true){
            //累计读取的字节数
            int byteRead = 0;
            while (byteRead < messageLength){
                byteRead += socketChannel.read(byteBuffers);
                System.out.println("累计读取的字节数： " +byteRead);
                Arrays.asList(byteBuffers)
                        .stream()
                        .map(buffer -> "postion: "+ buffer.position() + "limit: "+ buffer.limit())
                        .forEach(System.out::println);
            }
            //将所有buffer全部反转
            Arrays.asList(byteBuffers).stream().forEach(ByteBuffer::flip);

            //将数据显示回客户端
            int byteWrite = 0;
            while (byteWrite < messageLength){
                byteWrite += socketChannel.write(byteBuffers);
            }
            //将所有buffer进行复位
            Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);

            System.out.println("byteRead: "+ byteRead + " byteWrite: "+byteWrite + " messageLength: "+ messageLength);
        }
    }
}
