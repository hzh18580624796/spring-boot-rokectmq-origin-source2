package com.hzh.app.javalock.core;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition {


    //FIXME 锁的核心就是  LockSupport.park();  LockSupport.unpark();
    // 只有一个线程可以执行，其他的全部挂起
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();


        //LockSupport.park();

        new Thread(() -> {
            lock.lock();
            System.out.println("1");
            try {
                //FIXME await 一定要在signal前执行，不然signal执行时，condition等待队列没有数据，
                //FIXME 就不能将condition等待队列里移除元素到aqs队列中

                //FIXME 加入condition等待队列
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("哈哈");

            lock.unlock();
        }).start();


        new Thread(() -> {
            lock.lock();
            System.out.println("2");
            //FIXME 从condition等待队列移除，加入到aqs等待队列；
            //FIXME 如aqs上一个节点的状态被取消了, 或者尝试设置上一个节点的状态为 SIGNAL 失败了，需要唤醒刚加入aqs队列节点中的线程
            //FIXME 否则aqs阻塞队列自己唤醒。
            condition.signal();

            lock.unlock();
        }).start();
    }
}
