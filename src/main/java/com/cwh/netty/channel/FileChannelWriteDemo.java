package com.cwh.netty.channel;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * @ClassName : FileChannelDemo
 * @Description : 通过通道将数据写入本地文件
 * @Author : cuiweihua
 * @Date: 2021-01-10 11:29
 */
public class FileChannelWriteDemo {
    @SneakyThrows
    public static void main(String[] args) {
        //将字符串写入文本文件
        String str  = "cuiweihua 真牛逼！！！";
        //创建文件
        File file = new File("netty.txt");
        //创建输入流
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //获取通道
        FileChannel channel = fileOutputStream.getChannel();
        //创建缓存
        ByteBuffer buffer = ByteBuffer.allocate(512);
        //字符串放入缓存
        buffer.put(str.getBytes());
        //将buffer反转
        buffer.flip();
        //将缓存内容写入通道
        channel.write(buffer);
        //关闭通道
        channel.close();

    }
}
