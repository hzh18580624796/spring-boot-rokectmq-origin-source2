package com.hzh.app.javac;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class HzhApplicationRunner implements ApplicationRunner {

    public Object run(ApplicationContext context) {
        Map<String, ExecutorService> beans = context.getBeansOfType(ExecutorService.class);

        System.out.println("beans=" + JSONObject.toJSONString(beans));

        return "success";

    }
}
