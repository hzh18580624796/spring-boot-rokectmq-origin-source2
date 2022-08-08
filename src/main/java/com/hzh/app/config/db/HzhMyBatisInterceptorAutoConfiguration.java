package com.hzh.app.config.db;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * SqlSessionFactory 添加MyBatisInterceptor，但是在PageInterceptor之后
 *
 * @author hzh
 */
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
public class HzhMyBatisInterceptorAutoConfiguration {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void addPageInterceptor() {
        MyBatisInterceptor interceptor = new MyBatisInterceptor();

        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}
