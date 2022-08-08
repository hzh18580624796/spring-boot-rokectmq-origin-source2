package com.hzh.app.flow.example.flow;

import com.hzh.app.flow.CallBackTemplate;
import com.hzh.app.flow.CallBackable;
import com.hzh.app.flow.Flow;
import com.hzh.app.flow.FlowMaster;
import com.hzh.app.flow.example.dto.HzhContext;
import com.hzh.app.flow.example.dto.HzhRequest;
import com.hzh.app.flow.example.dto.HzhResponse;
import com.hzh.app.threadPool.HzhExecutorServiceWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class HzhFlow implements Flow<HzhContext, HzhResponse>, CallBackable<HzhContext> {

    @Autowired
    private HzhExecutorServiceWrapper executorServiceWrapper;
    @Autowired
    private CallBackTemplate<HzhContext> template;
    @Autowired
    private FlowMaster<HzhContext> master;

    private String flowName = "-hzh子流程";

    public String flowName(HzhContext context) {
        context.setFlowName(context.getFlowName() + flowName);
        return context.getFlowName();
    }

    @Override
    public HzhResponse flowApply(HzhContext context) {
        return startStep(flowName(context))
                .step("进件1").predicate(c -> true).invoke(this::tt)
                .step("进件2").end(this::value)
                .build()
                .apply(context);
    }

    private void tt(HzhContext context) {
        log.info("tt");
    }

    private HzhResponse value(HzhContext context) {
        log.info("value");
        master.publishEvent("hzhSon", context);

        //异步执行
        executorServiceWrapper.submit(() -> template.callInvoke(this, context), null);

        HzhResponse response = new HzhResponse();
        response.setStatus("yes or success");

        return response;
    }

    @Override
    public void call(HzhContext context) {
        HzhRequest request = context.getRequest();

        log.info("id={}",request.getId());
        log.info("name={}",request.getName());
    }

    public static void main(String[] args) {
        String[] strings = {"Hello", "World", "hzh"};
        List<String> collect = Stream.of(strings)
                .map(s -> s.split(""))
                .flatMap(s -> Stream.of(s))
                //.distinct()
                .collect(Collectors.toList());

//        String[] strings = {"Hello", "World","hzh"};
//        List<String[]> collect = Stream.of(strings)
//                .map(s -> s.split(""))
//                .collect(Collectors.toList());

        collect.forEach(c -> {
            System.out.println(c);
        });


        Set<String> set = new HashSet<>();
        set.add("hzh");
        set.add("hzh");

        System.out.println();

    }
}
