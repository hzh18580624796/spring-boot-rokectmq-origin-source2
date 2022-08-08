package com.hzh.app.threadPool.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.function.Supplier;

@Slf4j
public class RunnableWrapper implements Runnable {

    private Runnable source;
    private String hzhTid;
    private Supplier<String> supplier;

    public RunnableWrapper(Runnable source, String hzhTid) {
        this.source = source;
        this.hzhTid = hzhTid;
        this.supplier = this::get;
    }


    @Override
    public void run() {

        String result = supplier.get();
        //log.info("RunnableWrapper 执行Runnable任务-done,result={}", result);
    }

    private String get() {

        try {
            MDC.put("hzh-tid", hzhTid);

            log.info("RunnableWrapper 执行Runnable任务-start");
            source.run();
            log.info("RunnableWrapper 执行Runnable任务-done");

            return "success";
        } finally {
            MDC.clear();
        }


    }
}
