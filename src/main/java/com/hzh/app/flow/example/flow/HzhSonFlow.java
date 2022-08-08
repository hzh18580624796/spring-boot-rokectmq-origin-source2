package com.hzh.app.flow.example.flow;

import com.hzh.app.flow.Flow;
import com.hzh.app.flow.example.dto.HzhSonContext;
import com.hzh.app.flow.example.dto.HzhSonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HzhSonFlow implements Flow<HzhSonContext, HzhSonResponse> {

    private String flowName = "-hzhson流程";

    public String flowName(HzhSonContext context) {
        context.setFlowName(context.getFlowName() + flowName);
        return context.getFlowName();
    }

    @Override
    public HzhSonResponse flowApply(HzhSonContext context) {
        return startStep(flowName(context))
                .step("还款1").invoke(this::son)
                .step("还款2").end(this::value)
                .build()
                .apply(context);
    }

    private void son(HzhSonContext context) {
        log.info("son");
    }

    private HzhSonResponse value(HzhSonContext context) {
        log.info("还款成功");

        HzhSonResponse response = new HzhSonResponse();
        response.setStatus("this is hzh son");

        return response;
    }
}
