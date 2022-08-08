package com.hzh.app.flow.example.mapper;

import com.hzh.app.flow.Flow;
import com.hzh.app.flow.example.dto.HzhContext;
import com.hzh.app.flow.example.dto.main.HzhMainContext;
import com.hzh.app.flow.example.dto.main.HzhMainRequest;
import com.hzh.app.flow.example.dto.HzhRequest;
import com.hzh.app.flow.example.dto.HzhResponse;
import com.hzh.app.flow.example.flow.HzhFlow;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class HzhFlowMapper implements FlowMapper<HzhMainContext, HzhContext, HzhResponse> {

    @Autowired
    private HzhFlow subflow;

    @Override
    public HzhContext buildSubContext(HzhMainContext parentContext) {
        //初始化动作
        HzhContext context = new HzhContext();
        initAction(parentContext, context);

        //do other something
        HzhMainRequest mainRequest = parentContext.getMainRequest();

        HzhRequest request = new HzhRequest();
        request.setId(mainRequest.getId());
        request.setName("hzh....");

        context.setRequest(request);
        return context;
    }

    @Override
    public void acceptSubResponse(HzhResponse response, HzhMainContext mainContext) {

        mainContext.setHzhResponse(response);
    }

    @Override
    public String handleBusinessCode() {
        return "hzh";
    }

    @Override
    public Flow<HzhContext, HzhResponse> getSubFlow() {
        return subflow;
    }
}
