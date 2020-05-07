package com.learn.java_jdk.FutureAndPromise;

import java.util.concurrent.CompletableFuture;

/**
 * Created by wb-zj373670 on 2018/8/13.
 */
public class JdkCompletableFuture {
    public static void main(String[] args) {
        //对上一个阶段的结果进行变换，没有指定线程池，默认使用ForkJoinPool.commonPool()
        String result = CompletableFuture.supplyAsync(()->"hello").thenApply(s -> s + " word").join();
        //对上一步的结果进行消耗
//        CompletableFuture.supplyAsync(()->"hello").thenAccept(s -> System.out.println(s + " word"));
        //对上一步的结果不关心（没有入参），等上一步执行完成进行下一步
//        CompletableFuture.supplyAsync(() -> {
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            System.out.println("hello");
//            return "Hello";
//        }).thenRun(() -> System.out.println("hello world"));

        //结合两个CompletionStage结果，进行转化后返回
//        String result = CompletableFuture.supplyAsync(() -> {
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "hello";
//        }).thenCombine(CompletableFuture.supplyAsync(() -> {
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "world";
//        }), (s1, s2) -> s1 + s2).join();
//        System.out.println(result);

        //结合两个CompletionStage结果进行消耗 thenAcceptBoth和 thenAcceptBothAsyn
//        CompletableFuture.supplyAsync(() ->{
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "Hello";
//        }).thenAcceptBoth(CompletableFuture.supplyAsync(() ->{
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return " world";
//        }), (s1, s2) -> System.out.println(s1 + s2));
//        while (true){}

        //在两个CompletionStage都完成后执行runAfterBoth，runAfterBothAsync
//        CompletableFuture.supplyAsync(()->{
//            try{
//                Thread.sleep(4000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            System.out.println("Completable-one");
//            return "hello";
//        }).runAfterBoth(CompletableFuture.supplyAsync(() ->{
//            try{
//                Thread.sleep(4000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            System.out.println("Completable-two");
//            return "world";
//        }), () -> System.out.println("Hello world"));
//        while(true){}

        //两个CompletionStage，谁执行的快，就用那个执行结果进行下一步的转换applyToEither，applyToEitherAysnc
//       String string = CompletableFuture.supplyAsync(() -> {
//            try{
//                Thread.sleep(2020);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "Hello";
//        }).applyToEither(CompletableFuture.supplyAsync(() -> {
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//               e.printStackTrace();
//            }
//            return "Hi";
//        }), s -> s).join();
//        System.out.println(string);


        //两个CompletionStage谁执行的快，就用谁的结果进行消耗操作acceptEither，acceptEitherAsync
//        CompletableFuture.supplyAsync(() ->{
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "Hello";
//        }).acceptEither(CompletableFuture.supplyAsync(() ->{
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "Hi";
//        }), s -> System.out.println(s));
//        while (true){}

        //两个CompletionStage，任何一个完成了都会执行下一步操作runAfterEither，runAfterEitherAsync
//        CompletableFuture.supplyAsync(()->{
//            try {
//                Thread.sleep(5000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "Hello";
//        }).runAfterEither(CompletableFuture.supplyAsync(() ->{
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "Hi";
//        }), () -> System.out.println("Hello World"));
//        while (true){}

        //当运行时出现了异常，可以exceptionally进行补偿
//        String string = CompletableFuture.supplyAsync(() -> {
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            if(1 ==1){
//                throw new RuntimeException("测试异常");
//            }
//            return "Hello";
//        }).exceptionally(e -> {
//            System.out.println(e.getMessage());
//            return "Hi";
//        }).join();
//        System.out.println(string);

        //当运行完成时，对结果进行操作或记录,不进行消耗，原来有返回值的还是有返回值，whenComplete，whenCompleteAsync
//            String string = CompletableFuture.supplyAsync(() -> {
//                try{
//                    Thread.sleep(2000);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                if(1 == 1){
//                    throw new RuntimeException("测试异常");
//                }
//                return "hello";
//            }).whenComplete((s,t) -> {
//                System.out.println(s);
//                if(t != null){
//                    System.out.println(t.getMessage());
//                }
//            }).exceptionally(e -> {
//                System.out.println(e.getMessage());
//                return "hi";
//            }).join();
//        System.out.println(string);

        //运行完成时，对结果的处理。这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断。handle、handleAysnc
//            String result = CompletableFuture.supplyAsync(() -> {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                //出现异常
//                if (1 == 1) {
//                    throw new RuntimeException("测试一下异常情况");
//                }
//                return "s1";
//            }).handle((s, t) -> {
//                if (t != null) {
//                    return "hello world";
//                }
//                return s;
//            }).join();
//            System.out.println(result);


    }


}
