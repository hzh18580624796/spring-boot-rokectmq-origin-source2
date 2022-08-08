package com.hzh.app.flow;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallBackTemplate<Context> {

    public void callInvoke(CallBackable<Context> callback, Context context) {

        log.info("异步线程中执行回调处理，contextClass={}，context={}", context.getClass().getSimpleName(), JSONObject.toJSONString(context));
        callback.call(context);
    }
}
