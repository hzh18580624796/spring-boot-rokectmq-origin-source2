package com.hzh.app.web;

import com.hzh.app.event.HzhApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HzhApplicationEventController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/hzhEvent")
    public void hzhEvent() {
        log.info("HzhApplicationEventController hzhEvent");
        applicationContext.publishEvent(new HzhApplicationEvent("hzhEvent"));
    }
}
