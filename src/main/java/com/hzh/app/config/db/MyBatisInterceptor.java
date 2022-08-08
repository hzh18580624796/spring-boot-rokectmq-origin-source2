package com.hzh.app.config.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;


@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args =
                        {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args =
                        {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
        }
)
@Slf4j
public class MyBatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        try {
            // 获取xml中的一个select/update/insert/delete节点，是一条SQL语句
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object parameter = null;
            // 获取参数，if语句成立，表示sql语句有参数，参数格式是map形式
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }
            String sqlId = mappedStatement.getId(); // 获取到节点的id,即sql语句的id
            log.info("sqlId = " + sqlId);

            //BoundSql boundSql = mappedStatement.getBoundSql(parameter); // BoundSql就是封装myBatis最终产生的sql类

            //应为java的引用指向问题 这里boundSql需要从参数中取
            BoundSql boundSql = (BoundSql) invocation.getArgs()[5];

            Configuration configuration = mappedStatement.getConfiguration(); // 获取节点的配置
            String realSql = getRealSql(configuration, boundSql, sqlId); // 获取到最终的sql语句


            String[] lines = realSql.split("\n");
            StringBuilder manCanReadSql = new StringBuilder("");
            for (String line : lines) {
                if (!line.trim().equals("")) {
                    manCanReadSql.append(line + "\n");
                }
            }
            log.info("manCanReadSql = " + manCanReadSql);

        } catch (Exception e) {
            e.printStackTrace();
        }


        // 执行完上面的任务后，不改变原有的sql执行过程
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    // 封装了一下sql语句，使得结果返回完整xml路径下的sql语句节点id + sql语句
    public static String getRealSql(Configuration configuration, BoundSql boundSql, String sqlId) {
        String sql = doGetRealSql(configuration, boundSql);
        StringBuilder str = new StringBuilder(100);
        str.append(sql);
        return str.toString();
    }

    //进行？的替换
    public static String doGetRealSql(Configuration configuration, BoundSql boundSql) {

        //获取参数
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // sql语句中多个空格都用一个空格代替
        //String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        String sql = boundSql.getSql();

        if (CollectionUtils.isEmpty(parameterMappings) || parameterObject == null) {
            return sql;
        }


        //获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        //如果根据parameterObject.getClass(）可以找到对应的类型，则替换
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
        } else {

            //MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值,
            //主要支持对JavaBean、Collection、Map三种类型对象的操作
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    //该分支是动态sql
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                } else {
                    //打印出缺失，提醒该参数缺失并防止错位
                    sql = sql.replaceFirst("\\?", "缺失");
                }
            }
        }

        return sql;
    }

    // 如果参数是String，则添加单引号， 如果是日期，则转换为时间格式器并加单引号； 对参数是null和不是null的情况作了处理
    private static String getParameterValue(Object obj) {
        String value = null;

        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }

        return value;
    }
}


