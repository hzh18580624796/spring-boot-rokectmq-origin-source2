package com.hzh.app.event;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HzhApplicationEventListener {

    @EventListener
    @Async
    public void hzhApplicationEvent(HzhApplicationEvent event) {
        String hzhTid = MDC.get("hzh-tid");
        System.out.println("hzhTid= " + hzhTid);
        log.info("hzhApplicationEvent");
    }
}
