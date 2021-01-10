package com.cwh.netty.channel;

import java.nio.ByteBuffer;

/**
 * @ClassName : ReadOnlyBufferDemo
 * @Description : 只读缓存
 * @Author : cuiweihua
 * @Date: 2021-01-10 12:27
 */
public class ReadOnlyBufferDemo {
    public static void main(String[] args) {
        //创建缓存
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (int i = 0; i < 100 ; i++) {
            //放入数据
            buffer.put((byte) i);
        }
        //转为只读缓存
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        //循环读取    hasRemaining()判断是否有剩余
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
    }
}
