package daily.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

/**
 * Created by wb-zj373670 on 2018/5/4.
 */
public class TimeServcerHandler implements Runnable {
    private Socket clientProxy;

    public TimeServcerHandler(Socket socket){
        this.clientProxy = socket;
    }
    @Override
    public void run() {
//        System.out.println("run");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try{
            reader = new BufferedReader(new InputStreamReader(clientProxy.getInputStream()));
            writer = new PrintWriter(clientProxy.getOutputStream());
            while(true){
                String request = reader.readLine();
                if(!"GET CURRENT TIME".equals(request)){
                    System.out.println("BAD_REQUEST");
                } else {
                    writer.println(Calendar.getInstance().getTime().toLocaleString());
                    writer.flush();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            try{
                reader.close();
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
