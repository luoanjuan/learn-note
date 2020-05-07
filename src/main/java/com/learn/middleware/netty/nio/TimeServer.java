package com.learn.middleware.netty.nio;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * Created by wb-zj373670 on 2018/5/6.
 */
public class TimeServer {
    private BlockingQueue<SocketChannel> idleQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Future<SocketChannel>> workingQueue = new LinkedBlockingQueue<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    {
        new Thread(){
            @Override
            public void run() {
                try {
                    while (true) {
                        //task1 迭代idleQueue中的SocketChannel，提交到线程池中执行任务，并将其移交到workingQueue中
                        for (int i = 0; i < idleQueue.size(); i++) {
                            SocketChannel socketChannel = idleQueue.poll();
                            if (socketChannel != null) {
                                Future<SocketChannel> result = executor.submit(new TimeServerHandleTask(socketChannel), socketChannel);
                                workingQueue.put(result);
                            }
                        }

                        //task2  迭代当前workingQueue中的socketChannel，如果任务执行完成，将其移到idleQueue中
                        for(int j = 0; j < workingQueue.size();j++){
                            Future<SocketChannel> future = workingQueue.poll();
                            if(!future.isDone()){
                                workingQueue.put(future);
                                continue;
                            }
                            SocketChannel channel = null;
                            try{
                                channel = future.get();
                                idleQueue.put(channel);
                            }catch (Exception e){
                                channel.close();
                                e.printStackTrace();
                            }
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public static void main(String[] args) throws Exception {
        TimeServer timeServer = new TimeServer();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));
        while(true){
            SocketChannel socketChannel = ssc.accept();
            if(socketChannel == null){
                continue;
            }else {
                socketChannel.configureBlocking(false);
                timeServer.idleQueue.add(socketChannel);
            }
        }
    }
}

