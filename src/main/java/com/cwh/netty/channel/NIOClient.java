package com.cwh.netty.channel;

import lombok.SneakyThrows;

import java.awt.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : NIOClient
 * @Description :
 * @Author : cuiweihua
 * @Date: 2021-01-10 17:04
 */
public class NIOClient {
    @SneakyThrows
    public static void main(String[] args) {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(address)){
            if (!socketChannel.finishConnect()){
                while (true){
                        System.out.println("链接服务端需要事件，在此期间可以做其他事情");
                }
            }
        }
        String str = "hello";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
}
