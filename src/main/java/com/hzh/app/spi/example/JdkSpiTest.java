package com.hzh.app.spi.example;

import com.hzh.app.spi.Shape;

import java.util.ServiceLoader;

public class JdkSpiTest {

    public static void main(String[] args) {
        ServiceLoader<Shape> loaded = ServiceLoader.load(Shape.class);

        loaded.iterator().forEachRemaining(Shape::draw);
    }
}
