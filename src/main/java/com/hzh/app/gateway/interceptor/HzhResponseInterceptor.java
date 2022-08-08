package com.hzh.app.gateway.interceptor;

import com.hzh.app.gateway.GateWayContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HzhResponseInterceptor implements GatewayResponseInterceptor {

    public static HzhResponseInterceptor logResponseInterceptor = new HzhResponseInterceptor();

    @Override
    public <Request, Response> void after(GateWayContext<Request, Response> context) {

        Object rowResult = context.getRowResult();

        context.getGatewayResponse().setResponse((Response) rowResult);
    }
}
