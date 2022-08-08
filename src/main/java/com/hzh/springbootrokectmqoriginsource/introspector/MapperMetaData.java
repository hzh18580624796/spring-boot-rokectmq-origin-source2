package com.hzh.springbootrokectmqoriginsource.introspector;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.Map;

@Data
public class MapperMetaData {
    private String mapper;
    private Class mapperClazz;
    private Class domainClazz;
    private Map<String, Method> domainPropertyDescriptorStore;
}
