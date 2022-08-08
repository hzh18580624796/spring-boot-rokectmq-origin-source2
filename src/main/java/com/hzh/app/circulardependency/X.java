package com.hzh.app.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class X {

    @Autowired
    private Y y;

    public void xx() {
        System.out.println("xx");
    }
}
