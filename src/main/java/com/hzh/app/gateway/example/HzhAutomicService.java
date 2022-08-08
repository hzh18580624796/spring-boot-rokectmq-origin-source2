package com.hzh.app.gateway.example;

import com.hzh.app.gateway.GateWayRequest;
import com.hzh.app.gateway.GateWayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HzhAutomicService {

    @Autowired
    private HzhInvoker hzhInvoker;

    public String tt(String request) {
        GateWayRequest<String> gatewayRequest = new GateWayRequest<>();

        gatewayRequest.setRequest(request);
        GateWayResponse<String> gatewayResponse = hzhInvoker.tt(gatewayRequest);

        if (gatewayResponse.isSuccess()) {
            return gatewayResponse.getResponse();
        } else {
            return null;
        }
    }

    public Integer add(Integer request) {
        GateWayRequest<Integer> gatewayRequest = new GateWayRequest<>();

        gatewayRequest.setRequest(request);
        GateWayResponse<Integer> gatewayResponse = hzhInvoker.add(gatewayRequest);

        if (gatewayResponse.isSuccess()) {
            return gatewayResponse.getResponse();
        } else {
            return null;
        }
    }
}
