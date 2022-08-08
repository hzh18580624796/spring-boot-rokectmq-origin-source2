package com.hzh.app.flow.example.mapper;

import com.hzh.app.flow.Flow;
import com.hzh.app.flow.example.dto.HzhContext;
import com.hzh.app.flow.example.dto.HzhRequest;
import com.hzh.app.flow.example.dto.HzhSonContext;
import com.hzh.app.flow.example.dto.HzhSonRequest;
import com.hzh.app.flow.example.dto.HzhSonResponse;
import com.hzh.app.flow.example.flow.HzhSonFlow;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class HzhSonFlowMapper implements FlowMapper<HzhContext, HzhSonContext, HzhSonResponse> {

    @Autowired
    private HzhSonFlow subflow;

    @Override
    public HzhSonContext buildSubContext(HzhContext parentContext) {
        //初始化动作
        HzhSonContext context = new HzhSonContext();
        initAction(parentContext, context);

        //do other something
        HzhRequest mainRequest = parentContext.getRequest();

        HzhSonRequest request = new HzhSonRequest();
        request.setId(mainRequest.getId());
        request.setName("hzh son....");

        context.setRequest(request);
        return context;
    }

    @Override
    public void acceptSubResponse(HzhSonResponse response, HzhContext mainContext) {

        mainContext.setHzhSonResponse(response);
    }

    @Override
    public String handleBusinessCode() {
        return "hzhSon";
    }

    @Override
    public Flow<HzhSonContext, HzhSonResponse> getSubFlow() {
        return subflow;
    }
}
