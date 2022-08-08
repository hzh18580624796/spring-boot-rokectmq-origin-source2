package com.hzh.app.gateway;

import lombok.Data;

@Data
public class GateWayContext<Request, Response> {

    private String description;
    private GateWayRequest<Request> gatewayRequest;
    private Object rowResult;
    private GateWayResponse<Response> gatewayResponse = new GateWayResponse<>();
    private Throwable exception;

}
