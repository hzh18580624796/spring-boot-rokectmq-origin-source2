package com.hzh.app.javalock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCompletableFuture2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1
        System.out.println("1=======>");
        CompletableFuture.completedFuture("hzh")
                .thenApply(in -> {
                    System.out.println(in);
                    return in.toUpperCase();
                })
                .thenAccept(in -> {
                    System.out.println(in);
                })
                .thenRun(() -> {
                    System.out.println("then call");
                });


        //2
        System.out.println("2=======>");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture.anyOf(
                CompletableFuture.completedFuture("hzh")
                        .thenApplyAsync(in -> {
                            System.out.println(Thread.currentThread().getName());
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(in);
                            return in.toUpperCase();
                        }, executor),
                CompletableFuture.completedFuture("hzp")
                        .thenApplyAsync(in -> {
                            System.out.println(Thread.currentThread().getName());
                            System.out.println(in);
                            return in.toUpperCase();
                        }, executor)
        ).thenApply(in -> {
            System.out.println("main==>" + Thread.currentThread().getName());
            System.out.println("开看谁先到" + in);
            return null;
        });

        executor.shutdown();

        //3
        System.out.println("3=======>");
        ExecutorService executor2 = Executors.newFixedThreadPool(3);
        CompletableFuture.completedFuture("hzh")
                .thenApplyAsync(in -> {
                    System.out.println(Thread.currentThread().getName());
                    return in.toUpperCase() + "1";
                }, executor2)
                .thenApplyAsync(in -> {
                    System.out.println(Thread.currentThread().getName());
                    return in.toUpperCase() + "2";
                }, executor2)
                .thenCombine(CompletableFuture.completedFuture("hzp")
                        .thenApply(in -> {
                            return in.toUpperCase();
                        }), (x, y) -> {
                    System.out.println("x=" + x + " and y= " + y);
                    return null;
                });
        executor2.shutdown();

    }
}
