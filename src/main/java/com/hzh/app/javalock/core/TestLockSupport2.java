package com.hzh.app.javalock.core;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport2 {

    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char digital : digitalArr) {
                System.out.println(digital);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (char letter : letterArr) {
                LockSupport.park();
                LockSupport.unpark(t1);

                System.out.println(letter);
            }
        });

        t2.start();
        t1.start();
    }
}
