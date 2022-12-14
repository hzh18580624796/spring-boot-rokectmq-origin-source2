package com.hzh.app.javalock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalOutOfMemoryTest {
    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    // (1)
    final static ThreadPoolExecutor poolExecutor =
            new ThreadPoolExecutor(6, 6, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
    // (2)
    final static ThreadLocal localVariable = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        // (3)
        for (int i = 0; i < 50; ++i) {
            poolExecutor.execute(new Runnable() {
                public void run() {
                    // (4)
                    localVariable.set(new LocalVariable());
                    // (5)
                    System.out.println("use local varaible");
//                    localVariable.removeEvent();
                }
            });
            Thread.sleep(1000);
        }
        // (6)
        System.out.println("pool execute over");
    }
}
