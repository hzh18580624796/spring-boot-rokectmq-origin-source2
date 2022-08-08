package com.hzh.app.javalock.core;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestExchanger {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Producer("", exchanger).start();
        new Consumer("", exchanger).start();
    }

    static class Producer extends Thread {
        private Exchanger<Integer> exchanger;
        private static int data = 0;
        private AtomicInteger counter = new AtomicInteger(0);


        Producer(String name, Exchanger<Integer> exchanger) {
            super("Producer-" + name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    counter.getAndIncrement();
                    TimeUnit.SECONDS.sleep(1);
                    data = i;
                    System.out.println(getName() + counter.get() + " 交换前:" + data);
                    data = exchanger.exchange(data);
                    System.out.println(getName() + counter.get() + " 交换后:" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private Exchanger<Integer> exchanger;
        private static int data = 0;
        private AtomicInteger counter = new AtomicInteger(0);

        Consumer(String name, Exchanger<Integer> exchanger) {
            super("Consumer-" + name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {

                counter.getAndIncrement();
                if (counter.get() == 5) {
                    break;
                }
                data = 0;
                System.out.println(getName() + counter.get() + " 交换前:" + data);
                try {
                    TimeUnit.SECONDS.sleep(1);
                    data = exchanger.exchange(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + counter.get() + " 交换后:" + data);

            }
        }
    }
}

//Consumer-1 交换前:0
//Producer-1 交换前:1
//Consumer-1 交换后:1
//Producer-1 交换后:0

//Consumer-2 交换前:0
//Producer-2 交换前:2
//Producer-2 交换后:0
//Consumer-2 交换后:2

//Consumer-3 交换前:0
//Producer-3 交换前:3
//Producer-3 交换后:0
//Consumer-3 交换后:3

//Consumer-4 交换前:0
//Producer-4 交换前:4
//Producer-4 交换后:0
//Consumer-4 交换后:4
