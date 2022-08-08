package com.hzh.app.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class HzhThread {

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        return scheduledExecutorService;
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(3);
    }
}
