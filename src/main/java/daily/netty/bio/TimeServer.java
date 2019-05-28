package daily.netty.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wb-zj373670 on 2018/5/4.
 */
public class TimeServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try{
            server = new ServerSocket(8080);
            System.out.println("TimeServer started on 8080");
            while(true){
                Socket client = server.accept();
                System.out.println("Example");
                new Thread(new TimeServcerHandler(client)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                server.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

