package com.hzh.app.hzhevent;

import lombok.Data;

@Data
public class HzhEvent<EventSource> {

    private EventTypeEnums eventType;
    private EventSource eventSource;
}
