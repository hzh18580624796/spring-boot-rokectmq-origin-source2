package com.hzh.app.hzhevent;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.threadPool.HzhExecutorServiceWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HzhEventDispatcher {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private HzhExecutorServiceWrapper hzhExecutorServiceWrapper;
    private Map<String, List<HzhEventListener>> hzhEventListenerStore = new HashMap<>();

    @PostConstruct
    public void init() {

        Map<String, HzhEventListener> hzhEventListenerMap = applicationContext.getBeansOfType(HzhEventListener.class);
        hzhEventListenerMap.forEach((key, value) -> {

            String eventType = value.eventType().name();

            List<HzhEventListener> listeners = hzhEventListenerStore.getOrDefault(eventType, new ArrayList<>());
            listeners.add(value);

            hzhEventListenerStore.put(eventType, listeners);
        });
    }

    public void dispatcherSync(HzhEvent hzhEvent) {

        log.info("dispatcherSync,请求hzhEvent={}", JSONObject.toJSONString(hzhEvent));

        List<HzhEventListener> listeners = hzhEventListenerStore.get(hzhEvent.getEventType().name());

        listeners.forEach(ele -> {
            ele.onEvent(hzhEvent);
        });
    }

    public void dispatcherAsync(HzhEvent hzhEvent) {

        log.info("dispatcherAsync,请求hzhEvent={}", JSONObject.toJSONString(hzhEvent));

        List<HzhEventListener> listeners = hzhEventListenerStore.get(hzhEvent.getEventType().name());

        hzhExecutorServiceWrapper.submit(() -> {
            listeners.forEach(ele -> {
                ele.onEvent(hzhEvent);
            });

        }, null);
    }
}
