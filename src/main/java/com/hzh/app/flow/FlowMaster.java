package com.hzh.app.flow;

import com.hzh.app.flow.example.dto.AbstarctContext;
import com.hzh.app.flow.example.mapper.FlowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class FlowMaster<Context extends AbstarctContext> {

    @Autowired
    private ApplicationContext applicationContext;
    private Map<String, FlowWrapper> store = new HashMap<>();

    @PostConstruct
    public <SubContext extends AbstarctContext, Response, SubResponse> void init() {

        String[] beanNames = applicationContext.getBeanNamesForType(FlowMapper.class);

        Stream.of(beanNames).forEach(beanName -> {
            FlowMapper<Context, SubContext, SubResponse> mapper = (FlowMapper<Context, SubContext, SubResponse>) applicationContext.getBean(beanName);

            FlowWrapper<Context, Response, SubContext, SubResponse> wrapper = new FlowWrapper<>();
            wrapper.setSubFlow(mapper.getSubFlow());
            wrapper.setFunction(mapper::buildSubContext);
            wrapper.setBiConsumer(mapper::acceptSubResponse);
            store.putIfAbsent(mapper.handleBusinessCode(), wrapper);
        });
    }

    public void publishEvent(String eventName, Context mainContext) {
        store.get(eventName).flowApply(mainContext);
    }

}

//fixme 其他方式
//        Map<String, FlowMapper> beansOfTypes = applicationContext.getBeansOfType(FlowMapper.class);
//        beansOfTypes.forEach((beanName, v) -> {
//            FlowMapper<Context, SubContext, SubResponse> mapper = (FlowMapper<Context, SubContext, SubResponse>) applicationContext.getBean(beanName);
//            FlowWrapper<Context, Response, SubContext, SubResponse> wrapper = new FlowWrapper<>();
//            wrapper.setSubFlow(mapper.getSubFlow());
//            wrapper.setFunction(mapper::buildSubContext);
//            wrapper.setBiConsumer(mapper::acceptSubResponse);
//            store.put(mapper.handleBusinessCode(), wrapper);
//        });

//fixme 其他方式2
//       for (String beanName : beanNames) {
//           FlowMapper<Context, SubContext, SubResponse> mapper = (FlowMapper<Context, SubContext, SubResponse>) applicationContext.getBean(beanName);
//           FlowWrapper<Context, Response, SubContext, SubResponse> wrapper = new FlowWrapper<>();
//           wrapper.setSubFlow(mapper.getSubFlow());
//           wrapper.setFunction(mapper::buildSubContext);
//           wrapper.setBiConsumer(mapper::acceptSubResponse);
//           flowWrapperHashMap.put(mapper.handleBusinessCode(), wrapper);
//       }
