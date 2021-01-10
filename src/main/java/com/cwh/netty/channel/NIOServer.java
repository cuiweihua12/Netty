package com.cwh.netty.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName : NIOServer
 * @Description :
 * @Author : cuiweihua
 * @Date: 2021-01-10 16:25
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        //创建serversocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个selector
        Selector selector = Selector.open();
        //绑定端口进行监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞方式
        serverSocketChannel.configureBlocking(false);
        //将serverSocketChannel注册到selector 并关心连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //进行循环监听
        while (true){
            if (selector.select(1000) == 0){
                //没有事件发生
               // System.out.println("服务器监听了1m，没有事件发生。");
            }

            //获取selectorkey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历SelectionKey
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                //是否为链接事件
                if (selectionKey.isAcceptable()){
                    System.out.println("发生客户端链接");
                    //该客户端产生了一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector 并为socketChannel绑定一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                }

                //是否为读事件
                if (selectionKey.isReadable()){
                    //通过key反向获取channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //通过key反向获取buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    //将通道数据写入buffer
                    channel.read(buffer);

                    //打印
                    System.out.println("来自客户端消息：" + new String(buffer.array()));

                    //将读取到数据返回客户端
                    buffer.flip();
                    channel.write(buffer);

                    //最后重置buffer
                    buffer.clear();
                    }
                //移除key防止重复操作
                keyIterator.remove();
            }
        }


    }
}
