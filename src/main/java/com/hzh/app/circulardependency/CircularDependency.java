package com.hzh.app.circulardependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//fixme 这里可以简单的发现循环依赖的流程
public class CircularDependency {

    public static void main(String[] args) {

        ApplicationContext context = new HzhClassPathXmlApplicationContext("CircularDependencyApplicationContext.xml");

        //fixme 我日，用这个多爽
        //ApplicationContext context = new AnnotationConfigApplicationContext(CircularDependencyConfiguration.class);

        X x = (X) context.getBean("x");
        x.xx();

        System.out.println();
    }
}
