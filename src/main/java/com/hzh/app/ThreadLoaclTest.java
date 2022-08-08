package com.hzh.app;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLoaclTest {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        threadLocal.get();
        threadLocal.remove();

        AtomicInteger counter = new AtomicInteger(1);


        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(1, 3, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(3));
        for (; ; ) {
            executor.submit(() -> {
                System.out.println("1");
            });
        }

    }
}


