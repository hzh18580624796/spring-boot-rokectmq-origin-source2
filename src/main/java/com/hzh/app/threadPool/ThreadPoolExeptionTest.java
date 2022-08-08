package com.hzh.app.threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolExeptionTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
        //for (; ; ) {
            Future<Object> future = executorService.submit(() -> {
                System.out.println(1);
                throw new RuntimeException("ye");
            });
            System.out.println(future.get());
        }

    }
}


