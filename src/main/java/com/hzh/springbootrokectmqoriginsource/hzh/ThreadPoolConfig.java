package com.hzh.springbootrokectmqoriginsource.hzh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService executor1() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        return executor;
    }

    @Bean
    public ExecutorService executor2() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        return executor;
    }

    @Bean
    public ExecutorService executor3() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        return executor;
    }
}
