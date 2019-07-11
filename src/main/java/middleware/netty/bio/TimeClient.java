package middleware.netty.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wb-zj373670 on 2018/5/4.
 */
public class TimeClient {
    public static void main(String[] args) {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket client = null;
        try{
            client = new Socket("127.0.0.1", 8080);
            writer = new PrintWriter(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while(true){
                writer.println("GET CURRENT TIME");
                writer.flush();
                String response = reader.readLine();
                System.out.println("current time: " + response);
                Thread.sleep(5000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                writer.close();
                reader.close();
                client.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
