package com.hzh.app.threadPool.wrapper;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Slf4j
public class CallableWrapper<Response> implements Callable<Response> {

    private Callable<Response> source;
    private String hzhTid;
    private String hzhTid2;
    private Supplier<Response> supplier;


    public CallableWrapper(Callable<Response> source, String hzhTid, String hzhTid2) {
        this.source = source;
        this.hzhTid = hzhTid;
        this.hzhTid2 = hzhTid2;
        this.supplier = this::get;
    }

    @Override
    public Response call() throws Exception {
        return supplier.get();
    }

    public Response get() {

        try {
            MDC.put("hzh-tid", hzhTid);
            MDC.put("hzh-tid2", hzhTid2);

            log.info("CallableWrapper 执行Callable任务-start");
            Response response = null;
            try {
                response = source.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("CallableWrapper 执行Callable任务-done,result={}", JSONObject.toJSONString(response));

            return response;
        } finally {
            MDC.clear();
        }
    }
}
