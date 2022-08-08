package com.hzh.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class WebBeanController {

    @RestController
    public class TestController {

        @Autowired
        private WebBean webBean1;

        @GetMapping("/testController")
        public void testController() {
            System.out.println(webBean1);
        }

        @Bean
        public WebBean webBean1() {
            WebBean webBean = new WebBean();
            webBean.setName("1");
            return webBean;
        }

        @RestController
        public class TestController2 {

            @Autowired
            private WebBean2 webBean2;

            @GetMapping("/testController2")
            public void testController2() {
                System.out.println(webBean2);
            }

            @Bean
            public WebBean2 webBean2() {
                WebBean2 webBean2 = new WebBean2();
                webBean2.setName("2");
                return webBean2;
            }
        }
    }
}
