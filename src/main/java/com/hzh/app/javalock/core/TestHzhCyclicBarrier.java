package com.hzh.app.javalock.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class TestHzhCyclicBarrier {
    public static void main(String[] args) {
        HzhCyclicBarrier hzhCyclicBarrier = new HzhCyclicBarrier(3);
        hzhCyclicBarrier.setRunnable(() -> System.out.println("HzhCyclicBarrier Runnable"));

        new Thread(() -> {
            System.out.println("1 before");
            hzhCyclicBarrier.hzhAwait();
            System.out.println("1 after");
        }).start();

        new Thread(() -> {
            System.out.println("2 before");
            hzhCyclicBarrier.hzhAwait();
            System.out.println("2 after");
        }).start();

        new Thread(() -> {
            System.out.println("3 before");
            hzhCyclicBarrier.hzhAwait();
            System.out.println("3 after");
        }).start();

        System.out.println("done");
    }

    @Data
    public static class HzhCyclicBarrier {

        private CountDownLatch downLatch;
        private List<Thread> threadList = new ArrayList<>();
        private Runnable runnable;

        public HzhCyclicBarrier(int countNum) {
            downLatch = new CountDownLatch(countNum);

            System.out.println();
        }

        public void hzhAwait() {
            downLatch.countDown();
            if (downLatch.getCount() == 0) {
                runnable.run();
                //FIXME 唤醒其他线程处理自己，因为自己没有被park
                threadList.forEach(LockSupport::unpark);
                return;
            }
            threadList.add(Thread.currentThread());
            LockSupport.park();
        }
    }
}
