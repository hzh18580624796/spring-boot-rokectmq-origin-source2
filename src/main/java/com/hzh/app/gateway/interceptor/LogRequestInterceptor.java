package com.hzh.app.gateway.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.gateway.GateWayContext;
import com.hzh.app.gateway.GateWayRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogRequestInterceptor implements GatewayRequestInterceptor {

    public static LogRequestInterceptor logRequestInterceptor = new LogRequestInterceptor();


    @Override
    public <Request, Response> void pre(GateWayContext<Request, Response> context) {

        GateWayRequest<Request> gatewayRequest = context.getGatewayRequest();
        log.info("description = {},request = {}", context.getDescription(), JSONObject.toJSONString(gatewayRequest.getRequest()));
    }
}
