package com.hzh.app.web;

import com.hzh.app.gateway.GateWayRequest;
import com.hzh.app.gateway.GateWayResponse;
import com.hzh.app.gateway.example.HzhAutomicService;
import com.hzh.app.gateway.example.HzhInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @Autowired
    private HzhInvoker hzhInvoker;
    @Autowired
    private HzhAutomicService hzhAutomicService;

    @GetMapping("/hzhInvoker")
    public void hzhInvoker() {

        GateWayRequest<String> gatewayRequest = new GateWayRequest<>();

        gatewayRequest.setRequest("123");
        GateWayResponse<String> gatewayResponse = hzhInvoker.tt(gatewayRequest);

        System.out.println(gatewayResponse);
    }

    @GetMapping("/hzhAutomicServiceTt")
    public String hzhAutomicServiceTt(String in) {

        return hzhAutomicService.tt(in);
    }

    @GetMapping("/hzhAutomicServiceAdd")
    public Integer hzhAutomicServiceAdd(Integer in) {

        return hzhAutomicService.add(in);
    }
}
