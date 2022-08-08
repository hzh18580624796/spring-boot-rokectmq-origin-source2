package com.hzh.app.aopproxy;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class B {

    public void b1() {
        this.b2();
    }

    @Transactional
    public void b2() {
        System.out.println("b2");
    }
}
