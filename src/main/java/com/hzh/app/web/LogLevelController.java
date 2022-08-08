package com.hzh.app.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
public class LogLevelController {

    @GetMapping(value = "logLevel/{logLevel}")
    public String changeLogLevel(@PathVariable("logLevel") String logLevel) {
        try {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            Logger logger = loggerContext.getLogger("root");
            (logger).setLevel(Level.valueOf(logLevel));
        } catch (Exception e) {
            log.error("changeLogLevel error", e);
            return "fail";
        }
        return "success";
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(1);
        while (true) {

            executor.submit(() -> {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getForEntity("http://localhost:8080/springBootFile/hzhWebServlet", Object.class);
            });

        }
    }
}
