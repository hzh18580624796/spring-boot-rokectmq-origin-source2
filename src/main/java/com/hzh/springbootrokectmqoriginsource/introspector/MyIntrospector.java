package com.hzh.springbootrokectmqoriginsource.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyIntrospector {

    public static void main(String[] args) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {

        BeanInfo beanInfo = Introspector.getBeanInfo(InstanceTest.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();


        Map<String, Method> propertyDescriptorStore = new HashMap<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            propertyDescriptorStore.put(propertyDescriptor.getName(), propertyDescriptor.getWriteMethod());
        }

        InstanceTest instance = InstanceTest.class.newInstance();

        String param = "name";
        propertyDescriptorStore.get(param).setAccessible(true);
        propertyDescriptorStore.get(param).invoke(instance, "hezhihong");

        String param2 = "id";
        propertyDescriptorStore.get(param2).setAccessible(true);
        propertyDescriptorStore.get(param2).invoke(instance, 1);

        System.out.println(instance);

        Factory factory = new Factory();
        MapperMetaData mapperMetaData = new MapperMetaData();
        mapperMetaData.setDomainPropertyDescriptorStore(propertyDescriptorStore);
        factory.put("instanceTest", mapperMetaData);


        MapperMetaData mapperMetaDataFromFactory = factory.get("instanceTest");
        Map<String, Method> domainPropertyDescriptorStore = mapperMetaDataFromFactory.getDomainPropertyDescriptorStore();

        String param3 = "id";
        domainPropertyDescriptorStore.get(param3).setAccessible(true);
        domainPropertyDescriptorStore.get(param3).invoke(instance, 100);

        System.out.println(instance);


        String source = "com.hzh.springbootrokectmqoriginsource.introspector.MapperMetaData";
        System.out.println(lastWord(source));
    }

    public static String lastWord(String source) {
        return source.substring(source.lastIndexOf(".") + 1);
    }
}
