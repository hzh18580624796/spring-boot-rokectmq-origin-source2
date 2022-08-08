package com.hzh.app.threadPool.util;

import com.hzh.app.config.id.IdWorker;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

@Component
public class MDCThreadLocalUtil implements ApplicationContextAware {

    private static IdWorker idWorker;

    public static void setTidAndApplyAndThrowException(HzhRunnable run) throws IOException, ServletException {
        try {
            MDC.put("hzh-tid", idWorker.nextIdNo());
            run.run();
        } finally {
            MDC.clear();
        }
    }

    public static void setTidAndApply(Runnable run) {
        try {
            MDC.put("hzh-tid", idWorker.nextIdNo());
            run.run();
        } finally {
            MDC.clear();
        }
    }

    public static void setTid2AndApply(Runnable task) {
        try {
            MDC.put("hzh-tid2", idWorker.nextIdNo());
            task.run();
        } finally {
            MDC.remove("hzh-tid2");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MDCThreadLocalUtil.idWorker = (IdWorker) applicationContext.getBean("idWorker");
    }
}
