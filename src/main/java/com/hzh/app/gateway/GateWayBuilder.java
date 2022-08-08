package com.hzh.app.gateway;

import com.hzh.app.gateway.interceptor.GatewayRequestInterceptor;
import com.hzh.app.gateway.interceptor.GatewayResponseInterceptor;
import com.hzh.app.gateway.interceptor.LogRequestInterceptor;
import com.hzh.app.gateway.interceptor.LogResponseInterceptor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Data
public class GateWayBuilder<Request, Response> {

    private String description;
    private Function<Request, Response> function;
    private List<GatewayResponseInterceptor> responseInterceptorList = new ArrayList<>();

    public GateWayBuilder<Request, Response> description(String description) {
        this.description = "【" + description + "】";
        return this;
    }

    public GateWayBuilder<Request, Response> route(Function<Request, Response> function) {
        this.function = function;
        return this;
    }

    public GateWayBuilder<Request, Response> addLast(GatewayResponseInterceptor interceptor) {
        this.responseInterceptorList.add(interceptor);
        return this;
    }

    public GateWayTemplate<Request, Response> build() {

        List<GatewayRequestInterceptor> requestInterceptors = new ArrayList<>();
        requestInterceptors.add(LogRequestInterceptor.logRequestInterceptor);

        List<GatewayResponseInterceptor> responseInterceptors = new ArrayList<>();
        responseInterceptors.add(LogResponseInterceptor.logResponseInterceptor);
        responseInterceptors.addAll(responseInterceptorList);

        GateWayTemplate<Request, Response> template = new GateWayTemplate<>(
                this.description,
                this.function,
                requestInterceptors,
                responseInterceptors);
        return template;
    }
}
