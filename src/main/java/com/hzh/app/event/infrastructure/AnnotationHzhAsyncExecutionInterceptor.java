
package com.hzh.app.event.infrastructure;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.MDC;
import org.springframework.aop.interceptor.AsyncExecutionInterceptor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public class AnnotationHzhAsyncExecutionInterceptor extends AsyncExecutionInterceptor {

    public AnnotationHzhAsyncExecutionInterceptor(@Nullable Executor defaultExecutor) {
        super(defaultExecutor);
    }

    public AnnotationHzhAsyncExecutionInterceptor(@Nullable Executor defaultExecutor, AsyncUncaughtExceptionHandler exceptionHandler) {
        super(defaultExecutor, exceptionHandler);
    }

    @Override
    @Nullable
    protected String getExecutorQualifier(Method method) {
        // Maintainer's note: changes made here should also be made in
        // AnnotationAsyncExecutionAspect#getExecutorQualifier
        Async async = AnnotatedElementUtils.findMergedAnnotation(method, Async.class);
        if (async == null) {
            async = AnnotatedElementUtils.findMergedAnnotation(method.getDeclaringClass(), Async.class);
        }
        return (async != null ? async.value() : null);
    }

    @Override
    @Nullable
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
        Method specificMethod = ClassUtils.getMostSpecificMethod(invocation.getMethod(), targetClass);
        final Method userDeclaredMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        AsyncTaskExecutor executor = determineAsyncExecutor(userDeclaredMethod);
        if (executor == null) {
            throw new IllegalStateException(
                    "No executor specified and no default executor set on AsyncExecutionInterceptor either");
        }

        String hzhTid = MDC.get("hzh-tid");
        Callable<Object> task = () -> {
            try {
                MDC.put("hzh-tid", hzhTid);
                Object result = invocation.proceed();
                if (result instanceof Future) {
                    return ((Future<?>) result).get();
                }
            } catch (ExecutionException ex) {
                handleError(ex.getCause(), userDeclaredMethod, invocation.getArguments());
            } catch (Throwable ex) {
                handleError(ex, userDeclaredMethod, invocation.getArguments());
            } finally {
                //fixme by hzh 清理调 "hzh-tid"
                MDC.clear();
            }
            return null;
        };

        return doSubmit(task, executor, invocation.getMethod().getReturnType());
    }

}
