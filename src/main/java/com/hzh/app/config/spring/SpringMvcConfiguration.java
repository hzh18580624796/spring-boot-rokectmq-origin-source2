package com.hzh.app.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private AppInterceptor appInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(appInterceptor);
    }
}
