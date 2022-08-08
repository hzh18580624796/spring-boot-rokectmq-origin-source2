package com.hzh.app;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.config.id.IdWorker;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HzhBeanFactoryPostProcessor implements BeanFactoryPostProcessor, ApplicationRunner {

    //fixme 这个注入为空，因为BeanFactoryPostProcessor加载时机太早了，
    //fixme autowiredAnnotationBeanPostProcessor和commonAnnotationBeanPostProcessor都没加入到ioc中，
    //fixme 这个时候无法实现自动注入
    @Autowired
    private IdWorker idWorker;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println(JSONObject.toJSONString(beanFactory));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("HzhBeanFactoryPostPtrocessor ApplicaionRunner call");
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
    }
}
