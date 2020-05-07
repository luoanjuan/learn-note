package com.learn.middleware.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * Created by wb-zj373670 on 2018/5/6.
 */
public class SelectSockets {
    public static int PORT_NUMBER = 1234;

    public static void main(String[] args) throws Exception{
        new SelectSockets().go(args);
    }

    public void go(String[] args) throws Exception{
        int port = PORT_NUMBER;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }
        System.out.println("Listening on port " + port);
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverChannel.socket();
        Selector selector = Selector.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
        SelectionKey selectionKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while(true){
            int n = selector.select();
            if(n == 0){
                continue;
            }
            java.util.Iterator iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = (SelectionKey)iterator.next();
                if(key.isAcceptable()){
                    ServerSocketChannel server =(ServerSocketChannel) key.channel();
                    SocketChannel socket = server.accept();
                    registerChannel(selector, socket, SelectionKey.OP_READ);
                    sayHello(socket);
                }
                if(key.isReadable()){
                    readDataFromSocket(key);
                }
                iterator.remove();
            }
        }
    }

    protected void registerChannel(Selector selector, SelectableChannel channel, int ops) throws Exception{
        if(channel == null){
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }

    private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    protected void readDataFromSocket(SelectionKey key) throws Exception{
        SocketChannel socketChannel = (SocketChannel)key.channel();
        int count;
        buffer.clear();
        while((count = socketChannel.read(buffer)) > 0){
            buffer.flip();
            while(buffer.hasRemaining()){
                socketChannel.write(buffer);
            }
            buffer.clear();
        }
        if(count < 0){
            socketChannel.close();
        }
    }

    protected void sayHello(SocketChannel channel) throws Exception{
        buffer.clear();
        buffer.put("Hi there.\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }

}
