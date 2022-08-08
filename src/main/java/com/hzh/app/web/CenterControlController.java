package com.hzh.app.web;

import com.hzh.app.orderbusiness.core.BusinessEvent;
import com.hzh.app.orderbusiness.core.BusinessResponse;
import com.hzh.app.orderbusiness.core.CenterControl;
import com.hzh.app.orderbusiness.core.GateWayResponse;
import com.hzh.app.orderbusiness.hander.context.CreditContext;
import com.hzh.app.orderbusiness.hander.dto.CreditRequest;
import com.hzh.app.orderbusiness.hander.dto.CreditResponse;
import com.hzh.app.orderbusiness.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CenterControlController {

    @Autowired
    private CenterControl centerControl;
    @Autowired
    private OrderService orderService;

    @GetMapping("/centerControlInfo")
    public Map<String, Map<String, String>> centerControlInfo() {

        return centerControl.getBusinessSynAsynHanderNameStore();
    }

    @GetMapping("/orderCreditSynFire")
    public BusinessResponse orderCreditSynFire() {

        BusinessEvent<CreditContext, CreditRequest, CreditResponse> businessEvent = new BusinessEvent<>();

        CreditRequest request = new CreditRequest();
        request.setRequestData("hzh credit");

        CreditContext context = new CreditContext();
        context.setCreditRequest(request);

        businessEvent.setContext(context);
        businessEvent.setRequest(request);
        businessEvent.setEvent("credit");
        businessEvent.setFunctionInvoker(orderService::credit);


        BusinessResponse businessResponse = centerControl.publishEvent(businessEvent);

        return businessResponse;
    }

    @GetMapping("/orderCreditAsynInvoke")
    public BusinessResponse orderCreditAsynInvoke() {

        GateWayResponse<CreditResponse> gateWayResponse = new GateWayResponse<>();
        gateWayResponse.setSuccess(true);

        CreditResponse creditResponse = new CreditResponse();
        gateWayResponse.setResponse(creditResponse);

        creditResponse.setData("this is creditResponse syn data");

        BusinessResponse businessResponse = centerControl.asynDownStream(gateWayResponse, "", "credit");

        return businessResponse;
    }
}
