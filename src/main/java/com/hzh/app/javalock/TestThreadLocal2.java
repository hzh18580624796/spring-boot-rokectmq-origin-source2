package com.hzh.app.javalock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadLocal2 {

    public static class LocalVariable {
        private long[] a = new long[1024 * 1024];
    }

    final static ExecutorService executor = Executors.newFixedThreadPool(6);

    final static ThreadLocal<LocalVariable> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 50; i++) {
            executor.submit(() -> {
                threadLocal.set(new LocalVariable());
                System.out.println("use local varaible");
                //threadLocal.removeEvent();
            });

            //Thread.sleep(1000);
        }

        System.out.println("pool execute over");
    }
}
