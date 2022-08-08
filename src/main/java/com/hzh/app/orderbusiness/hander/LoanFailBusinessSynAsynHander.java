package com.hzh.app.orderbusiness.hander;

import com.hzh.app.orderbusiness.core.BusinessEventListener;
import com.hzh.app.orderbusiness.core.BusinessResponse;
import com.hzh.app.orderbusiness.core.GateWayResponse;
import com.hzh.app.orderbusiness.core.GatewayResponseStatus;
import com.hzh.app.orderbusiness.hander.context.LoanContext;
import com.hzh.app.orderbusiness.hander.dto.LoanResponse;
import lombok.Data;

@Data
@BusinessEventListener(event = "loan", status = GatewayResponseStatus.Fail)
public class LoanFailBusinessSynAsynHander extends BusinessSynAsynHander<LoanContext, LoanResponse> {
    private String name = "LoanFailBusinessSynAsynHander";


    //fixme 整一个flow进来
    //@Autowired
    //private Flow flow;

    @Override
    public boolean check(GateWayResponse<LoanResponse> gateWayResponse){
        return false;
    }

    @Override
    LoanContext asynContextPrepare(GateWayResponse<LoanResponse> gateWayResponse) {

        System.out.println("LoanFailBusinessSynAsynHander asynContextPrepare");
        return new LoanContext();
    }

    @Override
    void accumulate(LoanContext loanContext, GateWayResponse<LoanResponse> gateWayResponse) {
        System.out.println("LoanFailBusinessSynAsynHander accumulate");
    }

    @Override
    BusinessResponse handle(LoanContext loanContext) {

        //flow.do
        BusinessResponse businessResponse = new BusinessResponse();
        businessResponse.setStatus("S");
        return businessResponse;
    }
}
