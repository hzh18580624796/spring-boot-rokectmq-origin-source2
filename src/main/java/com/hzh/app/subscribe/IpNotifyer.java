package com.hzh.app.subscribe;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class IpNotifyer implements Notifyer {

    private List<String> ips = new ArrayList<>();

    @Override
    public void addEvent(String key) {

        for (String ip : ips) {
            log.info("告诉ip={},key={}被添加", ip, key);
        }
    }

    @Override
    public void removeEvent(String key) {

        for (String ip : ips) {
            log.info("告诉ip={},key={}被删除", ip, key);
        }
    }

    public void addIp(String ip) {
        if (ips.contains(ip)) {
            return;
        }
        ips.add(ip);
    }

    public void removeIp(String ip) {
        ips.remove(ip);
    }
}
