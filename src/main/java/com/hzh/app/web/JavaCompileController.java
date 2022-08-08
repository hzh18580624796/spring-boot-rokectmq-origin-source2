package com.hzh.app.web;

import com.hzh.app.javac.ApplicationRunner;
import com.hzh.app.javac.JavacRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class JavaCompileController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ScheduledExecutorService scheduledExecutorService;
    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, ScheduledFuture<?>> store = new HashMap<>();

    @GetMapping("/complierAndRun")
    public Object complierAndRun(String className) {

        try {
            String dir = System.getProperty("user.dir");
            System.out.println(dir);


            //动态编译
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int status = javac.run(null, null, null, "-d", System.getProperty("user.dir") + "/target/classes",
                    "/Users/hezhihong/Desktop/javacCode/" + className + ".java");
            if (status != 0) {
                System.out.println("没有编译成功！");
            }

            //动态执行
            Class clazz = Class.forName("com.hzh.app.javac." + className);//返回与带有给定字符串名的类 或接口相关联的 Class 对象。
            ApplicationRunner runner = (ApplicationRunner) clazz.newInstance();

            ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
                Object result = runner.run(applicationContext);

                System.out.println("================start");
                System.out.println("className=" + className + result);
                System.out.println("================end");
                System.out.println();

            }, 1, 1, TimeUnit.SECONDS);
            store.put(className, future);

            Object result = runner.run(applicationContext);

            return result;
        } catch (Exception e) {
            log.error("编译class，并执行异常", e);
        }

        return null;
    }

    @GetMapping("/remove")
    public String remove(String className) {
        ScheduledFuture<?> future = store.get(className);
        future.cancel(true);

        store.remove(className);

        return "success";
    }
}
