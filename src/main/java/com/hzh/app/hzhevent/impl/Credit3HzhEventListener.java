package com.hzh.app.hzhevent.impl;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.hzhevent.EventTypeEnums;
import com.hzh.app.hzhevent.HzhEvent;
import com.hzh.app.hzhevent.HzhEventListener;
import com.hzh.app.hzhevent.source.CreditEventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Credit3HzhEventListener implements HzhEventListener<CreditEventSource> {

    public void onEvent(HzhEvent<CreditEventSource> event) {
        CreditEventSource eventSource = event.getEventSource();

        log.info("写eventSource={}到rocket mq", JSONObject.toJSONString(eventSource));
    }

    @Override
    public EventTypeEnums eventType() {
        return EventTypeEnums.Credit;
    }
}
