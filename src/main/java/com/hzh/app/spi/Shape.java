package com.hzh.app.spi;

import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface Shape {

    void draw();
}
