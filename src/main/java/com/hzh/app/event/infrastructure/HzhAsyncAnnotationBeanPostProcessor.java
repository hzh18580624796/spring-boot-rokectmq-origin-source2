

package com.hzh.app.event.infrastructure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AnnotationAsyncExecutionInterceptor;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.concurrent.Executor;


@SuppressWarnings("serial")
public class HzhAsyncAnnotationBeanPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor {

    public static final String DEFAULT_TASK_EXECUTOR_BEAN_NAME =
            AnnotationAsyncExecutionInterceptor.DEFAULT_TASK_EXECUTOR_BEAN_NAME;


    protected final Log logger = LogFactory.getLog(getClass());

    @Nullable
    private Class<? extends Annotation> asyncAnnotationType;

    @Nullable
    private Executor executor;

    @Nullable
    private AsyncUncaughtExceptionHandler exceptionHandler;


    public HzhAsyncAnnotationBeanPostProcessor() {
        setBeforeExistingAdvisors(true);
    }


    public void setAsyncAnnotationType(Class<? extends Annotation> asyncAnnotationType) {
        Assert.notNull(asyncAnnotationType, "'asyncAnnotationType' must not be null");
        this.asyncAnnotationType = asyncAnnotationType;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void setExceptionHandler(AsyncUncaughtExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);

        HzhAsyncAnnotationAdvisor advisor = new HzhAsyncAnnotationAdvisor(this.executor, this.exceptionHandler);
        if (this.asyncAnnotationType != null) {
            advisor.setAsyncAnnotationType(this.asyncAnnotationType);
        }
        advisor.setBeanFactory(beanFactory);
        this.advisor = advisor;
    }

}
