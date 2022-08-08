package com.hzh.app.threadPool;

import com.hzh.app.threadPool.wrapper.CallableWrapper;
import com.hzh.app.threadPool.wrapper.RunnableWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
@Component
public class HzhExecutorServiceWrapper {

    @Autowired
    private ExecutorService executorService;

    public <T> Future<T> submit(Runnable task, T result) {

        Future<T> future = executorService.submit(new RunnableWrapper(task, "async-" + MDC.get("hzh-tid")), result);

        return future;
    }

    public <T> Future<T> submit(Callable<T> task) {

        Future<T> future = executorService.submit(new CallableWrapper<>(task, MDC.get("hzh-tid"), MDC.get("hzh-tid2")));

        return future;
    }
}
