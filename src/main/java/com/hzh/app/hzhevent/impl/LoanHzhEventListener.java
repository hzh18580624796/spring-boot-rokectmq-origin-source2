package com.hzh.app.hzhevent.impl;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.hzhevent.EventTypeEnums;
import com.hzh.app.hzhevent.HzhEvent;
import com.hzh.app.hzhevent.HzhEventListener;
import com.hzh.app.hzhevent.source.LoanEventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoanHzhEventListener implements HzhEventListener<LoanEventSource> {

    public void onEvent(HzhEvent<LoanEventSource> event) {
        LoanEventSource eventSource = event.getEventSource();

        log.info("写eventSource={}到日志", JSONObject.toJSONString(eventSource));
    }

    @Override
    public EventTypeEnums eventType() {
        return EventTypeEnums.Loan;
    }
}
