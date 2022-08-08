package com.hzh.app.config.db;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;

/**
 * @author Clinton Begin
 */
@Slf4j
public class HzhStdOutImpl2 implements Log {

    public HzhStdOutImpl2(String clazz) {
        // Do Nothing
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        System.err.println(s);
        e.printStackTrace(System.err);
    }

    @Override
    public void error(String s) {
        System.err.println(s);
    }

    @Override
    public void debug(String s) {
        //log.debug("HzhStdOutImpl2: " + s);
        log.info("HzhStdOutImpl2: " + s);
    }

    @Override
    public void trace(String s) {
        log.trace(s);
    }

    @Override
    public void warn(String s) {
        System.out.println(s);
    }
}
