package com.hzh.app.javalock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCompletableFuture3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //4
        System.out.println("4=======>");
        ExecutorService executor3 = Executors.newFixedThreadPool(2);
        List<CompletableFuture<Integer>> completableFutureList = new ArrayList<>();
        CompletableFuture<Integer> completableFuture = CompletableFuture.completedFuture(0);
        for (int i = 0; i < 100; i++) {
            completableFuture = completableFuture.thenApplyAsync(in -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                return in + 1;
            }, executor3);

            completableFutureList.add(completableFuture);
            System.out.println(completableFuture);
        }
        completableFuture
                .thenApply(in -> {
                    System.out.println("thenApply= " + Thread.currentThread().getName());
                    System.out.println(in);
                    return null;
                });

        //Thread.sleep(2000);
        System.out.println(completableFuture.get());
        executor3.shutdown();

//        System.out.println("4=======>");
//        ExecutorService executor3 = Executors.newFixedThreadPool(3);
//        CompletableFuture<Integer> completableFuture = CompletableFuture.completedFuture(0);
//        CompletableFuture<Integer> future =
//                completableFuture
//                        .thenApplyAsync(in -> {
//                            try {
//                                Thread.sleep(5000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println(Thread.currentThread().getName());
//                            return in + 1;
//                        }, executor3)
//                        .thenApplyAsync(in -> {
//                            try {
//                                Thread.sleep(5000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println(Thread.currentThread().getName());
//                            return in + 2;
//                        }, executor3);
//
//        future.get();
//        executor3.shutdown();

    }
}
