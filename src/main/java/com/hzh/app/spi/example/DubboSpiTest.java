package com.hzh.app.spi.example;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.hzh.app.spi.Shape;

public class DubboSpiTest {

    public static void main(String[] args) {
        ExtensionLoader<Shape> loaded = ExtensionLoader.getExtensionLoader(Shape.class);

        //fixme 按需加载，这里按key加载
        Shape rect = loaded.getExtension("rect");
        rect.draw();

        Shape circle = loaded.getExtension("circle");
        circle.draw();

        //fixme 按需加载，这里按adaptive加载
        Shape adaptive = loaded.getAdaptiveExtension();
        adaptive.draw();

        System.out.println();
    }
}
