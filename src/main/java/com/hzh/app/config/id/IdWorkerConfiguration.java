package com.hzh.app.config.id;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdWorkerConfiguration {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1, 1);
    }
}
