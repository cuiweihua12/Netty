package com.cwh.netty.channel;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @ClassName : FileChannelTransferFrom
 * @Description : 通过通道拷贝文件
 * @Author : cuiweihua
 * @Date: 2021-01-10 12:09
 */
public class FileChannelTransferFrom {
    @SneakyThrows
    public static void main(String[] args) {
        //创建流
        FileInputStream fileInputStream = new FileInputStream("sky.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("sky2.jpg");

        //获取通道
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        //拷贝文件
        outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

        //关闭流
        fileInputStream.close();
        fileOutputStream.close();

    }
}
