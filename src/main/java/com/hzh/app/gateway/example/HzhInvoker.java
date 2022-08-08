package com.hzh.app.gateway.example;

import com.hzh.app.gateway.GateWayBuilder;
import com.hzh.app.gateway.GateWayRequest;
import com.hzh.app.gateway.GateWayResponse;
import com.hzh.app.gateway.interceptor.HzhResponseInterceptor;
import com.hzh.app.gateway.interceptor.HzhStatusResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HzhInvoker {

    @Autowired
    private HzhService hzhService;

    public GateWayResponse<String> tt(GateWayRequest<String> gatewayRequest) {
        return new GateWayBuilder<String, String>()
                .description("客户管理系统：上次excel文件")
                .route(request -> hzhService.tt(request))
                .addLast(new HzhResponseInterceptor())
                .addLast(new HzhStatusResponseInterceptor())
                .build()
                .invoke(gatewayRequest);
    }

    public GateWayResponse<Integer> add(GateWayRequest<Integer> gatewayRequest) {
        return new GateWayBuilder<Integer, Integer>()
                .description("客户管理系统：随机加10以内的数")
                .route(request -> hzhService.add(request))
                .addLast(new HzhResponseInterceptor())
                .addLast(new HzhStatusResponseInterceptor())
                .build()
                .invoke(gatewayRequest);
    }
}
