package com.hzh.springbootrokectmqoriginsource.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaStream {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1"));
        userList.add(new User("2"));
        userList.add(new User("3"));
        userList.add(new User("4"));
        userList.add(new User("5"));
        userList.add(new User("6"));
        userList.add(new User("3"));

//        Map<String, User> collect = userList.stream().collect(Collectors.toMap(User::getName, ele -> ele));
        Map<String, List<User>> collect1 = userList.stream().collect(Collectors.groupingBy(User::getName));

        System.out.println();
    }
}
