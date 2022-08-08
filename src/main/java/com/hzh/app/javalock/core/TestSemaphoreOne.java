package com.hzh.app.javalock.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSemaphoreOne {

    public static void main(String[] args) throws InterruptedException {

        final Business business = new Business();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executor.execute(() -> business.service());
        }

        executor.shutdown();
    }

    private static class Business {

        private int count;

        Lock lock = new ReentrantLock();
        Semaphore sp = new Semaphore(1);

        public void service() {
            //lock.lock();
            try {
                sp.acquire(); //当前线程使用count变量的时候将其锁住，不允许其他线程访问
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            try {
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(count);

            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                //lock.unlock();
                sp.release();  //释放锁
            }
        }
    }
}


