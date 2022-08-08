package com.hzh.app.javalock;

import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//Generic通用的
public class TestGenericInterfaces {

    public static void main(String[] args) {
        TianZi tianZi = new TianZi20();

        Type[] types = tianZi.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];
        Type actualType = parameterizedType.getActualTypeArguments()[0];

        Class clazz = (Class) actualType;

        try {
            Object instance = clazz.newInstance();

            if (instance instanceof TianZiDomain) {
                TianZiDomain tianZiDomain = (TianZiDomain) instance;
                tianZiDomain.setName("黄天子");
                tianZiDomain.setPrice(20);

                System.out.println(tianZiDomain);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public interface TianZi<T> {
        T get();
    }

    public static class TianZi20 implements TianZi<TianZiDomain> {
        public TianZiDomain get() {
            return new TianZiDomain();
        }
    }

    @Data
    public static class TianZiDomain {
        private String name;
        private Integer price;
    }
}
