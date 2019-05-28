//package daily.test;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.parser.Feature;
//import com.google.common.util.concurrent.RateLimiter;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFactory;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.apache.log4j.spi.LoggerFactory;
//import org.redisson.Redisson;
//import org.redisson.api.RMap;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.redisson.config.TransportMode;
//import sun.plugin2.main.server.HeartbeatThread;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.net.InetSocketAddress;
//import java.util.*;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
///**
// * Created by wb-zj373670 on 2018/4/9.
// */
//public class Test {
//    public static void main(String[] args) throws Exception {
////        Object object = new Object();
////        Thread thread1 = new Thread(new Runnable() {
////            @Override
////            public void run() {
////                System.out.println("锁");
////                synchronized (object){
////                    System.out.println("获得锁");
////                }
////            }
////        });
////        thread1.start();
////        synchronized (object){
////            int sum = 0;
////            for(int i = 0; i < 1000000; i++){
////                sum += i;
////            }
////            thread1.interrupt();
////            System.out.println(thread1.isInterrupted());
////        }
////        while(true){}
//
//        ReentrantLock jdkz.JavaRMI.lock = new ReentrantLock();
//        Condition condition = jdkz.JavaRMI.lock.newCondition();
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    System.out.println("等待");
//                    jdkz.JavaRMI.lock.jdkz.JavaRMI.lock();
//                    condition.await();
//                    System.out.println("被中断");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    jdkz.JavaRMI.lock.unlock();
//                }
//
//            }
//        });
//        thread1.start();
//        jdkz.JavaRMI.lock.jdkz.JavaRMI.lock();
//        int sum = 0;
//        for(int i = 0; i<100000; i++){
//            sum += i;
//        }
////        thread1.interrupt();
//        System.out.println(thread1.isInterrupted());
//        condition.signal();
//        jdkz.JavaRMI.lock.unlock();
//        while (true){}
//
//
//    }
//
//
//}
//
