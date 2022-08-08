package com.hzh.app.javalock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class HzhCompletableFuture {

    public static void main(String[] args) {

        AtomicInteger threadCounter = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(4, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("HzhCompletableFuture-" + threadCounter.getAndIncrement());
                return thread;
            }
        });

        AtomicInteger total = new AtomicInteger(0);
        AtomicInteger counter = new AtomicInteger(0);
        List<CompletableFuture<Integer>> completableFutureList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CompletableFuture<Integer> future =
                    CompletableFuture
                            //使用异步线程
                            .supplyAsync(() -> {
                                Random r = new Random();
                                Integer value = r.nextInt(10);
                                System.out.println(value);
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return value;
                            }, executor)
                            //执行回调，将上一阶段的值，往下传递
                            .thenApply(in -> {
                                System.out.println("currentThread= " + Thread.currentThread().getName());
                                total.getAndAdd(in);
                                counter.getAndIncrement();
                                return null;
                            });
            completableFutureList.add(future);
        }

        //FIXME 必须阻塞到所有completableFuture执行完，不然线程池被关闭了，还有任务没有被执行
        completableFutureList.forEach(in -> {
            try {
                //FIXME CompletableFuture 在我这个版本有性能问题，如果只调用in.get(),需要改为in.get(Integer.MAX_VALUE, TimeUnit.MILLISECONDS)
                //FIXME 问题网址
                //FIXME https://www.toutiao.com/i7016913584307405350/?tt_from=dingtalk&utm_campaign=client_share&timestamp=1634049173&
                //FIXME app=news_article&utm_source=dingtalk&utm_medium=toutiao_ios&use_new_style=1&req_id=2021101222325301021207613035003048&
                //FIXME dtshare_count=1&group_id=7016913584307405350
                in.get(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        });

        System.out.println("total= " + total);
        System.out.println("counter= " + counter);
        executor.shutdown();
    }
}
