package com.hzh.app.javac;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class HzhJavacRunner implements JavacRunner {

    public Object run(JdbcTemplate jdbcTemplate) {

        System.out.println(jdbcTemplate);
        List<Map<String, Object>> query = jdbcTemplate.queryForList("select * from uuser");

        System.out.println(query);
        System.out.println(query.size());
        System.out.println("HzhJavacRunner");
        return query;
    }
}
