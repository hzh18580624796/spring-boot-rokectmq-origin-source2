package com.hzh.app.javalock.core;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("CyclicBarrier Runnable");
            }
        });

        new Thread(() -> {
            System.out.println("1 before");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("1 after");
        }).start();

        new Thread(() -> {
            System.out.println("2 before");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("2 after");
        }).start();

        System.out.println("done");
    }
}
