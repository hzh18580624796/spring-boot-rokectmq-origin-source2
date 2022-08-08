package com.hzh.app.config.scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

//fixme spring 配置类
@Configuration
public class ScheduledConfiguration {

    @Bean
    public TaskScheduler taskScheduler() {
        TaskScheduler scheduler = new ConcurrentTaskScheduler();
        return scheduler;
    }

    @Bean
    public ConfigurationBean configurationBean1() {
        //fixme Configuration 是full bean,taskScheduler()方法会被代理，根据方法名字从beanfacory中拿去bean
        //fixme 所以这里的scheduler是同一个bean
        TaskScheduler scheduler = taskScheduler();
        ConfigurationBean configurationBean = new ConfigurationBean();
        return configurationBean;
    }

    @Bean
    public ConfigurationBean configurationBean2() {
        //fixme Configuration 是full bean,taskScheduler()方法会被代理，根据方法名字从beanfacory中拿去bean
        //fixme 所以这里的scheduler是同一个bean
        TaskScheduler scheduler = taskScheduler();
        ConfigurationBean configurationBean = new ConfigurationBean();
        return configurationBean;
    }
}


//fixme 如果@Async+@EventListener，没有配置 TaskScheduler Bean的话，会执行下面的代码
//fixme return (defaultExecutor != null ? defaultExecutor : new SimpleAsyncTaskExecutor());
//fixme springboot 没有默认配置 TaskScheduler 对应的bean

//fixme TaskExecutor extend java.util.concurrent.Executor
//org.springframework.aop.interceptor.AsyncExecutionInterceptor.getDefaultExecutor


//@Override
//@Nullable
//protected Executor getDefaultExecutor(@Nullable BeanFactory beanFactory) {
//        Executor defaultExecutor = super.getDefaultExecutor(beanFactory);
//        return (defaultExecutor != null ? defaultExecutor : new SimpleAsyncTaskExecutor());
//        }
