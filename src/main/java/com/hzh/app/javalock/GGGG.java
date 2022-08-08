package com.hzh.app.javalock;

public class GGGG {

    static {
        instanseHzh = 10;
    }

    private static int instanseHzh = 20;


    public static void main(String[] args) {
        System.out.println(instanseHzh);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hzh");
        System.out.println(threadLocal.get());

        new Thread(() -> {
            threadLocal.set("thread1");
            System.out.println(threadLocal.get());
            ;
        }).start();

        new Thread(() -> {
            threadLocal.set("thread2");
            System.out.println(threadLocal.get());
            ;
        }).start();
    }
}

