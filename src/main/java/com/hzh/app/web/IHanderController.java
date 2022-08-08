package com.hzh.app.web;

import com.hzh.app.hander.HzhBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IHanderController {

    @Autowired
    private HzhBeanPostProcessor.IHandler handler;

    @GetMapping("/handler")
    public void handler() {
        handler.handle();
    }
}
