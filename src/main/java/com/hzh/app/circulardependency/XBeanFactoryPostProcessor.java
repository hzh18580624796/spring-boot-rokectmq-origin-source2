package com.hzh.app.circulardependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class XBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Autowired
    private X x;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        x.xx();
    }
}
