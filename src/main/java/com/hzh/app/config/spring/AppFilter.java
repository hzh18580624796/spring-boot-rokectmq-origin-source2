package com.hzh.app.config.spring;

import com.hzh.app.threadPool.util.MDCThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AppFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        MDCThreadLocalUtil.setTidAndApplyAndThrowException(() -> {
            log.info("AppFilter 经过");
            chain.doFilter(request, response);
        });
    }

    @Override
    public void destroy() {

    }
}
