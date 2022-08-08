package com.hzh.springbootrokectmqoriginsource.hzh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HzhExecutorConfig {

    @Bean
    public Executor hzhExecutor1() {
        return r -> {
            System.out.println("1");
            r.run();
        };
    }

    @Bean
    public Executor hzhExecutor2() {
        return r -> {
            System.out.println("2");
            r.run();
        };
    }

    @Bean
    public Executor hzhExecutor3() {
        return r -> {
            System.out.println("3");
            r.run();
        };
    }

    @Bean
    public Executor hzhExecutor4() {
        return r -> {
            System.out.println("4");
            r.run();
        };
    }
}
