package com.hzh.app.gateway.interceptor;

import com.hzh.app.gateway.GateWayContext;

public interface GatewayResponseInterceptor {

    <Request, Response> void after(GateWayContext<Request, Response> context);
}
