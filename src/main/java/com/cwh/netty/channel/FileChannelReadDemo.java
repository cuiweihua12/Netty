package com.cwh.netty.channel;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName : FileChannelReadDemo
 * @Description : 通过通道将数据从本地文件取出并打印
 * @Author : cuiweihua
 * @Date: 2021-01-10 11:52
 */
public class FileChannelReadDemo {
    @SneakyThrows
    public static void main(String[] args) {
        //获取文件
        FileInputStream fileInputStream = new FileInputStream("netty.txt");
        //获取通道
        FileChannel channel = fileInputStream.getChannel();
        //创建缓存
        ByteBuffer buffer = ByteBuffer.allocate(512);
        //将通道数据写入缓存
        channel.read(buffer);
        //打印
        System.out.println(new String(buffer.array()));
        //关闭通道
        channel.close();

    }
}
