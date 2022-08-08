package com.hzh.app.javalock;

import java.util.LinkedHashMap;

public class TestLinkedHashMap {

    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("hzh", "hzh");

        map.get("hzh");

        System.out.println(map);
    }
}
