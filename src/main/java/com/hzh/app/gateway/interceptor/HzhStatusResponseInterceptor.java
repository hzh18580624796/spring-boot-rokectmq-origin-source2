package com.hzh.app.gateway.interceptor;

import com.hzh.app.gateway.GateWayContext;
import com.hzh.app.gateway.GateWayStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HzhStatusResponseInterceptor implements GatewayResponseInterceptor {

    public static HzhStatusResponseInterceptor logResponseInterceptor = new HzhStatusResponseInterceptor();

    @Override
    public <Request, Response> void after(GateWayContext<Request, Response> context) {
        context.getGatewayResponse().setStatus(GateWayStatus.Success);
    }
}
