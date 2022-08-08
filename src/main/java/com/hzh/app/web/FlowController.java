package com.hzh.app.web;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.flow.example.dto.HzhContext;
import com.hzh.app.flow.example.dto.main.HzhMainContext;
import com.hzh.app.flow.example.dto.main.HzhMainRequest;
import com.hzh.app.flow.example.flow.HzhFlow;
import com.hzh.app.flow.example.dto.HzhRequest;
import com.hzh.app.flow.example.dto.HzhResponse;
import com.hzh.app.flow.example.mian.HzhMainFlow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.LockSupport;

@Slf4j
@RestController
public class FlowController {

    @Autowired
    private HzhFlow hzhFlow;
    @Autowired
    private HzhMainFlow hzhMainFlow;

    @GetMapping("/hzhFlow")
    public void hzhFlow(Integer id) {

        HzhRequest request = new HzhRequest();
        request.setId(id);
        request.setName("hezhihong---");


        HzhContext context = new HzhContext();
        context.setRequest(request);

        HzhResponse response = hzhFlow.flowApply(context);


        log.info("response= " + JSONObject.toJSONString(response));
    }

    @GetMapping("/hzhFlowOnce")
    public void hzhFlowOnce(Integer id) {

        HzhRequest request = new HzhRequest();
        request.setId(id);
        request.setName("hezhipeng---");


        HzhContext context = new HzhContext();
        context.setRequest(request);

        HzhResponse response = hzhFlow.flowApply(context);

        log.info("response= " + JSONObject.toJSONString(response));
    }

    @GetMapping("/hzhMainFlow")
    public void hzhMainFlow(Integer id) {
        HzhMainContext mainContext = new HzhMainContext();
        HzhMainRequest mainRequest = new HzhMainRequest();
        mainRequest.setId(id);

        mainContext.setMainRequest(mainRequest);

        hzhMainFlow.flowApply(mainContext);
    }

    public static void main(String[] args) {
        while (true) {

            LockSupport.park();
        }
    }

}
