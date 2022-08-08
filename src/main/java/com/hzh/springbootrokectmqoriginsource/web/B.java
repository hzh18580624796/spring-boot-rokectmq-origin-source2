package com.hzh.springbootrokectmqoriginsource.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class B {

    @Resource(name = "a")
    private Object a;
}
