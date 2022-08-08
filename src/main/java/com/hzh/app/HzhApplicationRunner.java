package com.hzh.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzh.app.web.WebBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
public class HzhApplicationRunner implements ApplicationRunner {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private BeanNameUrlHandlerMapping beanNameHandlerMapping;
    @Autowired
    private SimpleUrlHandlerMapping resourceHandlerMapping;
    @Autowired
    private ProjectInfoProperties projectInfoProperties;
    @Autowired
    private ObjectMapper jacksonObjectMapper;
    @Autowired
    private ScheduledExecutorService scheduledExecutorService;

    private static final int _1MB = 1024 * 1024;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("x");

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            WebBean webBean = new WebBean();
            byte[] databindata = new byte[5 * _1MB];


//            RestTemplate restTemplate = new RestTemplate();
//            String result = restTemplate.getForObject("http://localhost:8080/tt", String.class);
//            log.info("result={}", result);

        }, 1, 1, TimeUnit.MILLISECONDS);


//        Thread thread = new Thread();
//        thread.suspend();
//        AtomicInteger counter = new AtomicInteger(0);
//        counter.incrementAndGet();
//        counter.compareAndSet();
//        Lock lock = new ReentrantLock();
//        lock.lock();
//        new Thread(() -> {
//            for (; ; ) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
    }
}

//46360 SpringBootSingleApplication
//        -agentlib:jdwp=transport=dt_socket,
//        address=127.0.0.1:56245,
//        suspend=y,server=n
//        -XX:+PrintCommandLineFlags -XX:+UseG1GC -XX:TieredStopAtLevel=1 -Xverify:none -Dspring.output.ansi.enabled=always
//
//        -Dcom.sun.management.jmxremote
//        -Dspring.liveBeansView.mbeanDomain
//        -Dspring.application.admin.enabled=true
//        -javaagent:/Users/hezhihong/Library/Caches/IntelliJIdea2018.3/captureAgent/debugger-agent.jar -Dfile.encoding=UTF-8
