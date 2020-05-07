package com.learn.middleware.netty.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Calendar;

/**
 * Created by wb-zj373670 on 2018/5/6.
 */
public class TimeServerHandleTask implements Runnable{

    SocketChannel socketChannel;
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

    public TimeServerHandleTask(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try{
            ByteBuffer requestBuffer = ByteBuffer.allocate("GET CURRENT TIME".length());
            int bytesRead = socketChannel.read(requestBuffer);
            if(bytesRead <= 0){
                return;
            }
            while(requestBuffer.hasRemaining()){
                socketChannel.read(requestBuffer);
            }
            String requestStr = new String(requestBuffer.array());
            if(!"GET CURRENT TIME".equals(requestStr)){
                String responseStr = "BAD_REQUEST";
                ByteBuffer responseBuffer = ByteBuffer.allocate(responseStr.length());
                responseBuffer.put(responseStr.getBytes());
                responseBuffer.flip();
                socketChannel.write(responseBuffer);
            }else {
                String timeStr = Calendar.getInstance().getTime().toLocaleString();
                ByteBuffer responseBuffer = ByteBuffer.allocate(timeStr.length());
                responseBuffer.put(timeStr.getBytes());
                responseBuffer.flip();
                socketChannel.write(responseBuffer);
            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
