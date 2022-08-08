package com.hzh.app.web;

import com.alibaba.fastjson.JSONObject;
import com.hzh.springbootrokectmqoriginsource.web.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebMethod;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RestController
public class DemoController {

    //com.alipay.sofa.runtime.spring.ReferenceAnnotationBeanPostProcessor#processSofaReference
    @Autowired
    private ApplicationContext applicationContext;


    @GetMapping("/tt")
    public String tt() {
        log.info("tt");
        return "tt";
    }

    @Data
    public class AAA {
        private int id;
        private String name;
    }

    @GetMapping("/aaa")
    public AAA aaa(AAA in) {

        System.out.println(in);

        in.setId(in.getId() + 10);
        in.setName(in.getName() + "-modify by server");

        return in;
    }

    @GetMapping("/tt2")
    public User tt2(String a) {
        return new User("1");
    }

    @GetMapping("/null")
    public String nullx() {

        try {
            xx();
        } catch (Exception e) {
            log.info("异常={}", "", e);
        }

        return "null";
    }

    @GetMapping("/initializeBean")
    public void initializeBean() {
        Object bean = applicationContext.getBean("testController");
        applicationContext.getAutowireCapableBeanFactory().destroyBean("testController");
        applicationContext.getAutowireCapableBeanFactory().initializeBean(bean, "testController");
    }

    @GetMapping("/async")
    public void async(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();

        new Thread(() -> {
            try {
                PrintWriter writer = response.getWriter();

                for (int i = 0; i < 10; i++) {

                    Thread.sleep(3000);
                    writer.write("cc" + i);
                    writer.write(new WebBean().toString());
                    writer.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            asyncContext.complete();
        }).start();
    }

    @GetMapping("/async2")
    public void async2(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();

        new Thread(() -> {
            try {
                PrintWriter writer = response.getWriter();

                Thread.sleep(3000);

                WebBean webBean = new WebBean();
                webBean.setName("hzh");

                writer.write(JSONObject.toJSONString(webBean));
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            asyncContext.complete();
        }).start();
    }


    public void xx() {
        throw new NullPointerException();
    }
}
