package com.hzh.springbootrokectmqoriginsource.hzh;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HzhExecutorApplicationRunner implements ApplicationRunner {

    @Resource
    private Executor hzhExecutor1;
    @Resource
    private Executor hzhExecutor2;
    @Resource
    private Executor hzhExecutor3;
    @Resource
    private Executor hzhExecutor4;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        hzhExecutor1.execute(() -> {
            hzhExecutor2.execute(() -> {
                hzhExecutor3.execute(() -> {
                    hzhExecutor4.execute(() -> {
                        System.out.println("ok");
                    });
                });
            });
        });

    }
}
