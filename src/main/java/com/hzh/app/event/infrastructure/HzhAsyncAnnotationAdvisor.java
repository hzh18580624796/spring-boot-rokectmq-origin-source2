
package com.hzh.app.event.infrastructure;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Executor;

@SuppressWarnings("serial")
public class HzhAsyncAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private AsyncUncaughtExceptionHandler exceptionHandler;

    private Advice advice;

    private Pointcut pointcut;


    public HzhAsyncAnnotationAdvisor() {
        this(null, null);
    }

    @SuppressWarnings("unchecked")
    public HzhAsyncAnnotationAdvisor(@Nullable Executor executor, @Nullable AsyncUncaughtExceptionHandler exceptionHandler) {
        Set<Class<? extends Annotation>> asyncAnnotationTypes = new LinkedHashSet<>(2);
        asyncAnnotationTypes.add(Async.class);
        try {
            asyncAnnotationTypes.add((Class<? extends Annotation>)
                    ClassUtils.forName("javax.ejb.Asynchronous", HzhAsyncAnnotationAdvisor.class.getClassLoader()));
        } catch (ClassNotFoundException ex) {
            // If EJB 3.1 API not present, simply ignore.
        }
        if (exceptionHandler != null) {
            this.exceptionHandler = exceptionHandler;
        } else {
            this.exceptionHandler = new SimpleAsyncUncaughtExceptionHandler();
        }
        this.advice = buildAdvice(executor, this.exceptionHandler);
        this.pointcut = buildPointcut(asyncAnnotationTypes);
    }


    public void setTaskExecutor(Executor executor) {
        this.advice = buildAdvice(executor, this.exceptionHandler);
    }

    public void setAsyncAnnotationType(Class<? extends Annotation> asyncAnnotationType) {
        Assert.notNull(asyncAnnotationType, "'asyncAnnotationType' must not be null");
        Set<Class<? extends Annotation>> asyncAnnotationTypes = new HashSet<>();
        asyncAnnotationTypes.add(asyncAnnotationType);
        this.pointcut = buildPointcut(asyncAnnotationTypes);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }


    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }


    protected Advice buildAdvice(@Nullable Executor executor, AsyncUncaughtExceptionHandler exceptionHandler) {
        return new AnnotationHzhAsyncExecutionInterceptor(executor, exceptionHandler);
    }

    protected Pointcut buildPointcut(Set<Class<? extends Annotation>> asyncAnnotationTypes) {
        ComposablePointcut result = null;
        for (Class<? extends Annotation> asyncAnnotationType : asyncAnnotationTypes) {
            Pointcut cpc = new AnnotationMatchingPointcut(asyncAnnotationType, true);
            Pointcut mpc = new AnnotationMatchingPointcut(null, asyncAnnotationType, true);
            if (result == null) {
                result = new ComposablePointcut(cpc);
            } else {
                result.union(cpc);
            }
            result = result.union(mpc);
        }
        return (result != null ? result : Pointcut.TRUE);
    }

}
