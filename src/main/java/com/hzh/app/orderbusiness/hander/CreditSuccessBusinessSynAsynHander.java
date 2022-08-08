package com.hzh.app.orderbusiness.hander;

import com.hzh.app.orderbusiness.core.BusinessEvent;
import com.hzh.app.orderbusiness.core.BusinessEventListener;
import com.hzh.app.orderbusiness.core.BusinessResponse;
import com.hzh.app.orderbusiness.core.CenterControl;
import com.hzh.app.orderbusiness.core.GateWayResponse;
import com.hzh.app.orderbusiness.core.GatewayResponseStatus;
import com.hzh.app.orderbusiness.hander.context.CreditContext;
import com.hzh.app.orderbusiness.hander.dto.CreditResponse;
import com.hzh.app.orderbusiness.hander.context.LoanContext;
import com.hzh.app.orderbusiness.hander.dto.LoanRequest;
import com.hzh.app.orderbusiness.hander.dto.LoanResponse;
import com.hzh.app.orderbusiness.service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@BusinessEventListener(event = "credit", status = GatewayResponseStatus.Success)
public class CreditSuccessBusinessSynAsynHander extends BusinessSynAsynHander<CreditContext, CreditResponse> {
    private String name = "CreditSuccessBusinessSynAsynHander";

    //fixme 整一个flow进来
    //@Autowired
    //private Flow flow;

    @Autowired
    private CenterControl centerControl;

    @Autowired
    private OrderService orderService;

    @Override
    public boolean check(GateWayResponse<CreditResponse> gateWayResponse){
        return false;
    }

    @Override
    public CreditContext asynContextPrepare(GateWayResponse<CreditResponse> gateWayResponse) {
        System.out.println("CreditSuccessBusinessSynAsynHander asynContextPrepare");

        return new CreditContext();
    }

    @Override
    public void accumulate(CreditContext creditContext, GateWayResponse<CreditResponse> gateWayResponse) {
        System.out.println("CreditSuccessBusinessSynAsynHander accumulate");
    }

    @Override
    public BusinessResponse handle(CreditContext creditContext) {

        //flow.do
        BusinessEvent<LoanContext, LoanRequest, LoanResponse> businessEvent = new BusinessEvent<>();

        LoanRequest request = new LoanRequest();
        request.setRequestData("hzh loan");

        LoanContext context = new LoanContext();
        context.setLoanRequest(request);

        businessEvent.setContext(context);
        businessEvent.setRequest(request);
        businessEvent.setEvent("loan");
        businessEvent.setFunctionInvoker(orderService::loan);

        BusinessResponse businessResponse = centerControl.publishEvent(businessEvent);

        BusinessResponse otherBusinessResponse = new BusinessResponse();
        otherBusinessResponse.setStatus("S");
        return businessResponse;
    }
}
