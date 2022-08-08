package com.hzh.app.config.id;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class IdWorkerHelper implements ApplicationContextAware {

    private static IdWorker idWorker;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        IdWorkerHelper.idWorker = (IdWorker) applicationContext.getBean("idWorker");
    }

    public static String nextIdNo() {
        return idWorker.nextIdNo();
    }
}
