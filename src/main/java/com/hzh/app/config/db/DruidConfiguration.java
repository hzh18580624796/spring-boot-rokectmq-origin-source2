package com.hzh.app.config.db;

import com.hzh.app.config.db.mybatis.HzhSqlSessionInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.hzh.app.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DruidConfiguration {


    @Bean(name = "sqlSessionFactoryHzh")
    @Primary
    public SqlSessionFactory sqlSessionFactoryHzh(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/**/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.addInterceptor(new MyBatisInterceptor());

        configuration.setLogImpl(HzhStdOutImpl2.class);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean(name = "sqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryHzh") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryHzh") SqlSessionFactory sqlSessionFactory) {
        PersistenceExceptionTranslator exceptionTranslator =
                new MyBatisExceptionTranslator(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true);

        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(
                sqlSessionFactory,
                sqlSessionFactory.getConfiguration().getDefaultExecutorType(),
                exceptionTranslator);


        try {
            //private SqlSessionFactory sqlSessionFactory;
            //private ExecutorType executorType;
            //private SqlSession sqlSessionProxy;
            //private PersistenceExceptionTranslator exceptionTranslator;

            HzhSqlSessionInterceptor hzhSqlSessionInvocationHandler = new HzhSqlSessionInterceptor();
            hzhSqlSessionInvocationHandler.setSqlSessionFactory(sqlSessionFactory);
            hzhSqlSessionInvocationHandler.setExecutorType(sqlSessionFactory.getConfiguration().getDefaultExecutorType());
            hzhSqlSessionInvocationHandler.setExceptionTranslator(exceptionTranslator);

            SqlSession sqlSessionProxyByHzh = (SqlSession) Proxy.newProxyInstance(
                    SqlSessionFactory.class.getClassLoader(),
                    new Class[]{SqlSession.class},
                    hzhSqlSessionInvocationHandler);

            Field sqlSessionProxyField = sqlSessionTemplate.getClass().getDeclaredField("sqlSessionProxy");
            sqlSessionProxyField.setAccessible(true);
            sqlSessionProxyField.set(sqlSessionTemplate, sqlSessionProxyByHzh);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.warn("DruidConfiguration sqlSessionTemplate", e);
            e.printStackTrace();
        }

        return sqlSessionTemplate;
    }
}
