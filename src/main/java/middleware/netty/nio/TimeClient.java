package middleware.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by wb-zj373670 on 2018/5/6.
 */
public class TimeClient {
    static int connectTimeOut =  3000;
    static ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8080));
        socketChannel.configureBlocking(false);
        long start = System.currentTimeMillis();
        while(!socketChannel.finishConnect()){
            if(System.currentTimeMillis()-start >= connectTimeOut){
                throw new RuntimeException("尝试建立连接失败");
            }
        }
        while(true){
            buffer.put("GET CURRENT TIME".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            if(socketChannel.read(buffer) > 0){
                buffer.flip();
                byte[] response = new byte[buffer.remaining()];
                buffer.get(response);
                System.out.println("reveive response:" + new String(response));
                buffer.clear();
            }
            Thread.sleep(5000);
        }
    }
}
