package com.hzh.app.javac;

import org.springframework.jdbc.core.JdbcTemplate;

public interface JavacRunner {

    Object run(JdbcTemplate jdbcTemplate);
}
