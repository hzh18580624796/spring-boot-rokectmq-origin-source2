package com.hzh.app.javalock.core;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("我是线程在跑");
            LockSupport.park();//1-->0

            System.out.println("线程阻塞后继续运行");
        });


        thread.start();

        //Thread.sleep(4000);
        //注释和不注释
        //LockSupport.unpark(thread);//0--->1
        System.out.println("done");
    }
}
