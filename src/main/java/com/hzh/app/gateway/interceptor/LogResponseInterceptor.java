package com.hzh.app.gateway.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.gateway.GateWayContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogResponseInterceptor implements GatewayResponseInterceptor {

    public static LogResponseInterceptor logResponseInterceptor = new LogResponseInterceptor();

    @Override
    public <Request, Response> void after(GateWayContext<Request, Response> context) {
        log.info("description = {},response = {}", context.getDescription(), JSONObject.toJSONString(context.getRowResult()));
    }
}
