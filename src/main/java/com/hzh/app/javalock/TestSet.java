package com.hzh.app.javalock;

import java.util.HashSet;
import java.util.Set;

public class TestSet {

    public static void main(String[] args) {

        Set<String> sets = new HashSet<>();

        sets.add("hzh");
        sets.add(null);

        sets.forEach(x -> System.out.println(x));

        System.out.println(sets);
        Object[] objects = sets.toArray();
    }
}
