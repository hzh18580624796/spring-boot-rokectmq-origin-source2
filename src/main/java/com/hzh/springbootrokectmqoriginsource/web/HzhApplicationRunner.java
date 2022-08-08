package com.hzh.springbootrokectmqoriginsource.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HzhApplicationRunner implements ApplicationRunner {

    @Resource(name = "a")
    private Object a;
    @Autowired
    private XXX xxx;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println();
    }
}
