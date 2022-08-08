package com.hzh.app.gateway;

import com.hzh.app.gateway.interceptor.GatewayRequestInterceptor;
import com.hzh.app.gateway.interceptor.GatewayResponseInterceptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Data
public class GateWayTemplate<Request, Response> {

    private String description;
    private Function<Request, Response> function;
    private List<GatewayRequestInterceptor> requestInterceptorList;
    private List<GatewayResponseInterceptor> responseInterceptorList;

    public GateWayTemplate(String description,
                           Function<Request, Response> function,
                           List<GatewayRequestInterceptor> requestInterceptorList,
                           List<GatewayResponseInterceptor> responseInterceptorList) {
        this.description = description;
        this.function = function;
        this.requestInterceptorList = requestInterceptorList;
        this.responseInterceptorList = responseInterceptorList;
    }

    public GateWayResponse<Response> invoke(GateWayRequest<Request> gatewayRequest) {

        Request request = gatewayRequest.getRequest();

        GateWayContext<Request, Response> context = new GateWayContext<>();
        context.setDescription(description);
        context.setGatewayRequest(gatewayRequest);

        log.info("====>网关调用,description = {},start", description);

        requestInterceptorList.forEach(ele -> ele.pre(context));

        Object rowResult = function.apply(request);
        context.setRowResult(rowResult);

        responseInterceptorList.forEach(ele -> ele.after(context));

        log.info("<====网关调用,description = {},end", description);
        return context.getGatewayResponse();
    }

}
