package com.hzh.app.threadPool.util;

import javax.servlet.ServletException;
import java.io.IOException;

@FunctionalInterface
public interface HzhRunnable {

    void run() throws IOException, ServletException;
}
