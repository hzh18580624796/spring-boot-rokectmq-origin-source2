package com.hzh.app.web;

import com.alibaba.fastjson.JSONObject;
import com.hzh.app.subscribe.SubscribeStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeController {

    @Autowired
    private SubscribeStore subscribeStore;

    @GetMapping("/subscribe")
    public void subscribe(String key, String ip) {
        subscribeStore.subscribe(key, ip);
    }

    @GetMapping("/unSubscribe")
    public void unSubscribe(String key, String ip) {
        subscribeStore.unSubscribe(key, ip);
    }

    @GetMapping("/keyAdd")
    public void add(String key) {
        subscribeStore.add(key);
    }

    @GetMapping("/keyRemove")
    public void remove(String key) {
        subscribeStore.remove(key);
    }

    @GetMapping("/subscribeStore")
    public String subscribeStore() {
        return JSONObject.toJSONString(subscribeStore);
    }

}
