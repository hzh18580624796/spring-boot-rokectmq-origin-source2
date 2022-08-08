package com.hzh.app.config.db.mybatis;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.apache.ibatis.reflection.ExceptionUtil.unwrapThrowable;
import static org.mybatis.spring.SqlSessionUtils.closeSqlSession;
import static org.mybatis.spring.SqlSessionUtils.getSqlSession;
import static org.mybatis.spring.SqlSessionUtils.isSqlSessionTransactional;


@Slf4j
@Data
public class HzhSqlSessionInterceptor implements InvocationHandler {

    private SqlSessionFactory sqlSessionFactory;
    private ExecutorType executorType;
    private SqlSession sqlSessionProxy;
    private PersistenceExceptionTranslator exceptionTranslator;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession sqlSession = getSqlSession(
                sqlSessionFactory,
                executorType,
                exceptionTranslator);
        try {
            Object result = method.invoke(sqlSession, args);
            if (!isSqlSessionTransactional(sqlSession, sqlSessionFactory)) {
                // force commit even on non-dirty sessions because some databases require
                // a commit/rollback before calling close()
                sqlSession.commit(true);
            }

            log.info("===============================HzhSqlSessionInterceptor===============================");
            log.info("HzhSqlSessionInterceptor执行sql后，result={}", JSONObject.toJSONString(result));
            log.info("===============================HzhSqlSessionInterceptor===============================");

            return result;
        } catch (Throwable t) {

            log.warn("HzhSqlSessionInterceptor exception", t);

            Throwable unwrapped = unwrapThrowable(t);
            if (exceptionTranslator != null && unwrapped instanceof PersistenceException) {
                // release the connection to avoid a deadlock if the translator is no loaded. See issue #22
                closeSqlSession(sqlSession, sqlSessionFactory);
                sqlSession = null;
                Throwable translated = exceptionTranslator.translateExceptionIfPossible((PersistenceException) unwrapped);
                if (translated != null) {
                    unwrapped = translated;
                }
            }
            throw unwrapped;
        } finally {
            if (sqlSession != null) {
                closeSqlSession(sqlSession, sqlSessionFactory);
            }
        }
    }
}
