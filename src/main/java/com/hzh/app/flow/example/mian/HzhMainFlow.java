package com.hzh.app.flow.example.mian;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.flow.Flow;
import com.hzh.app.flow.FlowMaster;
import com.hzh.app.flow.example.dto.main.HzhMainContext;
import com.hzh.app.flow.example.dto.main.HzhMainResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HzhMainFlow implements Flow<HzhMainContext, HzhMainResponse> {

    @Autowired
    private FlowMaster<HzhMainContext> master;
    private String mainFlowName = "hzh主流程";

    public String flowName(HzhMainContext mainContext) {
        mainContext.setFlowName(mainContext.getFlowName() + mainFlowName);
        return mainFlowName;
    }

    @Override
    public HzhMainResponse flowApply(HzhMainContext mainContext) {
        return startStep(flowName(mainContext))
                .step("发布hzh事件").invoke(this::publishHzh)
                .step("发布hzh事件2").invoke(this::publishHzh2)
                .step("value").end(this::value)
                .build()
                .apply(mainContext);
    }

    private void publishHzh(HzhMainContext mainContext) {
        master.publishEvent("hzh", mainContext);
    }

    private void publishHzh2(HzhMainContext mainContext) {
        master.publishEvent("hzh2", mainContext);
    }

    private HzhMainResponse value(HzhMainContext mainContext) {

        log.info("mainContext={}", JSONObject.toJSONString(mainContext));
        HzhMainResponse mainResponse = new HzhMainResponse();
        return mainResponse;
    }
}
