package com.cwh.netty.channel;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName : FileChannelReadAndWrite
 * @Description : 读取文件数据并写入另一个文件
 * @Author : cuiweihua
 * @Date: 2021-01-10 11:58
 */
public class FileChannelReadAndWrite {
    @SneakyThrows
    public static void main(String[] args) {
        //创建输入流
        FileInputStream fileInputStream = new FileInputStream("netty.txt");
        //创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("netty2.txt");
        //获取输入流通道
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        //获取输出流通道
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        //创建缓存
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            //初始化缓存但不会清除缓存数据
            buffer.clear();
            //将通道数据写入缓存
            int read = inputStreamChannel.read(buffer);
            //如果read 等于-1 表示已经读完
            if (read == -1){
                break;
            }
            //反转缓存准备将数据写入通道
            buffer.flip();
            //将缓存数据写入通道
            outputStreamChannel.write(buffer);
        }

        //关闭通道
        inputStreamChannel.close();
        outputStreamChannel.close();
    }
}
