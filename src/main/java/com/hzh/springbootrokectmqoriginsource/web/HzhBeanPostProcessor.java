package com.hzh.springbootrokectmqoriginsource.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HzhBeanPostProcessor implements BeanPostProcessor, ApplicationRunner {

    @Resource(name = "a")
    private Object a;


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println();
    }
}
