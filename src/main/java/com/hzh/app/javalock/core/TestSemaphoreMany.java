package com.hzh.app.javalock.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestSemaphoreMany {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        final Semaphore sp = new Semaphore(3);//创建Semaphore信号量，初始化许可大小为3

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    sp.acquire();//请求获得许可，如果有可获得的许可则继续往下执行，许可数减1。否则进入阻塞状态
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有" + (3 - sp.availablePermits()) + "个并发");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                sp.release();//释放许可，许可数加1
            });
        }

        executor.shutdown();
    }
}


