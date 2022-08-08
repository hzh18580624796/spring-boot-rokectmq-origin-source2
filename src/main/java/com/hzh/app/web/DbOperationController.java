package com.hzh.app.web;

import com.github.pagehelper.PageHelper;
import com.hzh.app.canal.UserTmp;
import com.hzh.app.mapper.HzhMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class DbOperationController {

    @Resource
    private DataSource dataSource;
    @Autowired
    private Map<String, DataSource> dataSourceMap;
    @Resource
    private HzhMapper hzhMapper;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println();
    }

    @GetMapping("x")
    public void x() {
        PageHelper.startPage(1, 10);
        hzhMapper.seleect();
    }

    @GetMapping("seleectUserTmp")
    public void seleectUserTmp() {
        UserTmp u = new UserTmp();
        u.setId("0");
        u.setBig(BigDecimal.ZERO);

        UserTmp res = hzhMapper.seleectUserTmp(u);
        System.out.println(res);
    }

    @GetMapping("allName")
    public List<String> allName() {
        PageHelper.startPage(1, 10);

        List<String> allNames = hzhMapper.allName();

        List<String> allNames2 = hzhMapper.allName();
        return allNames;
    }
}
