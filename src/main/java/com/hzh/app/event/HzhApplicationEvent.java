package com.hzh.app.event;

import org.springframework.context.ApplicationEvent;

public class HzhApplicationEvent extends ApplicationEvent {

    public HzhApplicationEvent(Object source) {
        super(source);
    }
}
