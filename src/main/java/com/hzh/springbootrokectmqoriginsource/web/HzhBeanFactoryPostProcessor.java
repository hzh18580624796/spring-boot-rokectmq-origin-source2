package com.hzh.springbootrokectmqoriginsource.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

@Component
public class HzhBeanFactoryPostProcessor implements BeanFactoryPostProcessor, ApplicationRunner {

    @Resource(name = "a")
    private Object a;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();


        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("a");
        //beanDefinition.setDependsOn("xxx");

        beanDefinition = new RootBeanDefinition(AA.class);
        ((DefaultListableBeanFactory) beanFactory).removeBeanDefinition("a");
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("a", beanDefinition);


        BeanDefinition xxxBeanDefinition = new RootBeanDefinition(XXX.class);
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("x", xxxBeanDefinition);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();

        System.out.println(stopWatch);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println();
    }
}
