package com.hzh.springbootrokectmqoriginsource.hzh;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

@Component
public class ThreadPoolApplicationRunner implements ApplicationRunner {

    @Resource
    private ExecutorService executor1;
    @Resource
    private ExecutorService executor2;
    @Resource
    private ExecutorService executor3;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        executor1.submit(() -> {
            executor2.submit(() -> {
                executor3.submit(() -> {
                    System.out.println("yes");
                });
            });
        });
    }
}
