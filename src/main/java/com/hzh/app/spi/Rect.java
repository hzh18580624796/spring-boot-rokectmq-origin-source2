package com.hzh.app.spi;

public class Rect implements Shape {

    @Override
    public void draw() {
        System.out.println("画一个矩形");
    }
}
