package com.hzh.app.javalock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * FIXME 调用thenApplyAsync，后调用thenApply，那后面thenApply执行的时候，是用的前面异步线程池里的线程
 */
public class TestCompletableFuture_1thenApplyAsync_2thenApply {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> completableFuture = CompletableFuture.completedFuture(0);
        CompletableFuture<Object> future = completableFuture
                .thenApplyAsync(in -> {
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(Thread.currentThread().getName());
                    return in + 1;
                })
                //FIXME 这里真的会，前面是异步执行的话，thenApply会看前面的执行结果是不是已经完成，
                //FIXME 是：实现使用mian线程继续执行；
                //FIXME 不是：放到stack里，后续采用线程池里的线程一个一个执行stack里的任务，任务串行话了
                .thenApply(in -> {
                    System.out.println("thenApply= " + Thread.currentThread().getName());
                    System.out.println(in);
                    return null;
                });


        future.get();


        //FIXME xxxxxxx下一个
        //FIXME xxxxxxx下一个
        //FIXME xxxxxxx下一个
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.completedFuture(0);
        CompletableFuture<Object> future2 = completableFuture2
                .thenApply(in -> {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    return in + 1;
                })
                .thenApply(in -> {
                    System.out.println("thenApply= " + Thread.currentThread().getName());
                    System.out.println(in);
                    return null;
                });


        future2.get();
    }
}
