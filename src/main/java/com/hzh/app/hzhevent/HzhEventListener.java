package com.hzh.app.hzhevent;

public interface HzhEventListener<EventSource> {

    void onEvent(HzhEvent<EventSource> event);

    EventTypeEnums eventType();
}
