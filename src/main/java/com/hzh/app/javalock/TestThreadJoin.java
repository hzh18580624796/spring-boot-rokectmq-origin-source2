package com.hzh.app.javalock;

public class TestThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程执行完了");
        });

        thread.start();
        thread.join();
        System.out.println("main线程执行完了");
    }
}
