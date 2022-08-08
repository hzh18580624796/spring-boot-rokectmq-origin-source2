package com.hzh.app.hander;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class HzhBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        //fixme beanName="hzhBeanPostProcessor.AHandler"

        if (beanName.equals("aHandler")) {
            //if (bean instanceof IHandler) {

            bean = new BHandler((IHandler) bean);
        }

        return bean;
    }

    @Component("aHandler")
    public static class AHandler implements IHandler {

        @PostConstruct
        public void init() {
            System.out.println();
        }

        @Override
        public void handle() {

            System.out.println("AHandler");
        }
    }

    public class BHandler implements IHandler {

        //源
        private IHandler orign;

        public BHandler(IHandler orign) {
            this.orign = orign;
        }

        @Override
        public void handle() {
            orign.handle();
            System.out.println("BHandler");
        }
    }

    public interface IHandler {

        void handle();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/hezhihong/ideaWork/spring-boot-rokectmq-origin-source2/target/classes/com/hzh/app/hander");
        File file2 = new File("/Users/hezhihong/ideaWork/spring-boot-rokectmq-origin-source2/target/classes/com/hzh/app/web");

        System.out.println(file);
        System.out.println(file2);
    }


}
