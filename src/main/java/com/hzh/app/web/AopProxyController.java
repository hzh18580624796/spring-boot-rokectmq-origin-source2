package com.hzh.app.web;

import com.hzh.app.aopproxy.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;

@RestController
public class AopProxyController {

    @Autowired
    private B b;

    @GetMapping("/b1")
    public String b1() {
        b.b1();
        return "tt";
    }

    @GetMapping("/b2")
    public String b2() {
        b.b2();
        return "tt";
    }
}
