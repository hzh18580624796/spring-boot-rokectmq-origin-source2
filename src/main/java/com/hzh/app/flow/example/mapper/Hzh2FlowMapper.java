package com.hzh.app.flow.example.mapper;

import com.hzh.app.flow.Flow;
import com.hzh.app.flow.example.dto.Hzh2Context;
import com.hzh.app.flow.example.dto.Hzh2Response;
import com.hzh.app.flow.example.dto.main.HzhMainContext;
import com.hzh.app.flow.example.dto.main.HzhMainRequest;
import com.hzh.app.flow.example.dto.HzhRequest;
import com.hzh.app.flow.example.flow.Hzh2Flow;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class Hzh2FlowMapper implements FlowMapper<HzhMainContext, Hzh2Context, Hzh2Response> {

    @Autowired
    private Hzh2Flow subflow;

    @Override
    public Hzh2Context buildSubContext(HzhMainContext parentContext) {
        //初始化动作
        Hzh2Context context = new Hzh2Context();
        initAction(parentContext, context);

        //do other something
        HzhMainRequest mainRequest = parentContext.getMainRequest();

        HzhRequest request = new HzhRequest();
        request.setId(mainRequest.getId());
        request.setName("hzh2....");

        context.setRequest(request);
        return context;
    }

    @Override
    public void acceptSubResponse(Hzh2Response response, HzhMainContext mainContext) {

        mainContext.setHzh2Response(response);
    }

    @Override
    public String handleBusinessCode() {
        return "hzh2";
    }

    @Override
    public Flow<Hzh2Context, Hzh2Response> getSubFlow() {
        return subflow;
    }
}
