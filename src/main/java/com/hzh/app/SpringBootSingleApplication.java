package com.hzh.app;

import ch.qos.logback.classic.PatternLayout;
import com.hzh.app.config.log.HzhLineOfCallerConverter;
import com.hzh.app.event.infrastructure.EnableHzhAsync;
import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@EnableCanalClient //声明当前的服务是canal的客户端
@EnableScheduling
//@EnableAsync
@EnableHzhAsync
public class SpringBootSingleApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.defaultConverterMap.put("line", HzhLineOfCallerConverter.class.getName());

        SpringApplication.run(SpringBootSingleApplication.class, args);
    }

    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("SpringBootSingleApplication start");
        super.onStartup(servletContext);
    }
}
