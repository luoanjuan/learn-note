package middleware.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by wb-zj373670 on 2018/5/6.
 */
public class ChannelAccept {
    public static final String GREETING = "Hello, I must be going. \r\n";

    public static void main(String[] args) throws Exception{
        int port = 1234;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        while(true){
            System.out.println("Waiting for connections");
            SocketChannel sc = ssc.accept();
            if(sc == null){
                Thread.sleep(2000);
            }else {
                sc.configureBlocking(false);
                ByteBuffer allocate = ByteBuffer.allocate(16 * 1024);
                while(sc.read(allocate) > 0){
                    allocate.flip();
                    while(allocate.hasRemaining()){
                        byte b = allocate.get();
                        System.out.println(b);
                    }
                    allocate.clear();
                }
                System.out.println("Incoming connection from: " + sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            }

        }

    }
}
