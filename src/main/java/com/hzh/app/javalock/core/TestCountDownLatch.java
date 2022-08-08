package com.hzh.app.javalock.core;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //fixme 底层是case去每次 减1
            downLatch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            downLatch.countDown();
        }).start();
//        downLatch.countDown();

        downLatch.await();
        System.out.println();


    }
}
