package com.hzh.app.javalock;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadLocal {
    private static ThreadLocal<Object> integerThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

//        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
//        integerThreadLocal.set(9);
//        integerThreadLocal.set(19);
//        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
//        stringThreadLocal.set("hzh");
//
//        System.out.println(integerThreadLocal.get());
//        System.out.println(stringThreadLocal.get());

        AtomicInteger counter = new AtomicInteger(0);

        Thread.sleep(25000);

        System.out.println("开始");

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    integerThreadLocal.set(new Object());


//                        System.out.println("counter= " + counter.getAndIncrement() + "--->" + integerThreadLocal.get());
//                        Thread.sleep(3000);
//                        System.out.println("counter2= " + counter.getAndIncrement() + "--->" + integerThreadLocal.get());
                    integerThreadLocal.remove();
                    System.out.println("counter= " + counter.getAndIncrement());

                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
