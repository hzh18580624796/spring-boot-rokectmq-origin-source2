package com.hzh.springbootrokectmqoriginsource.introspector;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Factory {
    private Map<String, MapperMetaData> mapperMetaMap = new HashMap<>();

    public void put(String key, MapperMetaData value) {
        mapperMetaMap.put(key, value);
    }

    public MapperMetaData get(String key) {
        return mapperMetaMap.get(key);
    }
}
