package com.hzh.app.gateway.interceptor;

import com.hzh.app.gateway.GateWayContext;

public interface GatewayRequestInterceptor {

    <Request, Response> void pre(GateWayContext<Request, Response> context);

}
