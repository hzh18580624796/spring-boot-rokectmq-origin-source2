package com.hzh.app.flow.example.flow;

import com.hzh.app.flow.Flow;
import com.hzh.app.flow.example.dto.Hzh2Context;
import com.hzh.app.flow.example.dto.Hzh2Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Hzh2Flow implements Flow<Hzh2Context, Hzh2Response> {

    private String flowName = "-hzh2子流程";

    public String flowName(Hzh2Context context) {
        context.setFlowName(context.getFlowName() + flowName);
        return context.getFlowName();
    }

    @Override
    public Hzh2Response flowApply(Hzh2Context context) {
        return startStep(flowName(context))
                .step("支付1").predicate(c -> true).invoke(this::tt)
                .step("支付2").end(this::value)
                .build()
                .apply(context);
    }

    private void tt(Hzh2Context context) {
        log.info("支付1");
    }

    private Hzh2Response value(Hzh2Context context) {
        log.info("支付2");

        Hzh2Response response = new Hzh2Response();
        response.setStatus("pay ok");

        return response;
    }

}
