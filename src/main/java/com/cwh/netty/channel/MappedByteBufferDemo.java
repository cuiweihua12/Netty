package com.cwh.netty.channel;

import lombok.SneakyThrows;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName : MappedByteBufferDemo
 * @Description : MappedByteBuffer 可以直接在内存（堆内存之外）进行修改，操作系统不需要在拷贝一次
 * @Author : cuiweihua
 * @Date: 2021-01-10 12:43
 */
public class MappedByteBufferDemo {
    @SneakyThrows
    public static void main(String[] args) {
        /**
         * 参数一：文件
         * 参数二：读写模式
         */
        RandomAccessFile accessFile = new RandomAccessFile("netty.txt","rw");
        FileChannel channel = accessFile.getChannel();

        /**
         * 参数一：READ_WRITE  使用读写模式
         * 参数二：0：可以直接操作的开始位置
         * 参数三：5：映射到内存的大小  不是索引位置
         * 可以直接修改的范围就是0-5
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0,(byte)'N');
        map.put(1,(byte) 'B');
        accessFile.close();
    }
}
