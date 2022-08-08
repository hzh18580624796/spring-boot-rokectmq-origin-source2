package com.hzh.app.spi;

import com.alibaba.dubbo.common.extension.Adaptive;

@Adaptive
public class AdaptiveShape implements Shape {

    @Override
    public void draw() {
        System.out.println("画一个 Adaptive Shape");
    }
}
