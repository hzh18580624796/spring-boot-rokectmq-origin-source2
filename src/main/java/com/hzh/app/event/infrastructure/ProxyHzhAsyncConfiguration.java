package com.hzh.app.event.infrastructure;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.annotation.AbstractAsyncConfiguration;
import org.springframework.scheduling.config.TaskManagementConfigUtils;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyHzhAsyncConfiguration extends AbstractAsyncConfiguration {

    @Bean(name = TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public HzhAsyncAnnotationBeanPostProcessor asyncAdvisor() {
        Assert.notNull(this.enableAsync, "@EnableAsync annotation metadata was not injected");
        HzhAsyncAnnotationBeanPostProcessor bpp = new HzhAsyncAnnotationBeanPostProcessor();
        Class<? extends Annotation> customAsyncAnnotation = this.enableAsync.getClass("annotation");
        if (customAsyncAnnotation != AnnotationUtils.getDefaultValue(EnableHzhAsync.class, "annotation")) {
            bpp.setAsyncAnnotationType(customAsyncAnnotation);
        }
        if (this.executor != null) {
            bpp.setExecutor(this.executor);
        }
        if (this.exceptionHandler != null) {
            bpp.setExceptionHandler(this.exceptionHandler);
        }
        bpp.setProxyTargetClass(this.enableAsync.getBoolean("proxyTargetClass"));
        bpp.setOrder(this.enableAsync.<Integer>getNumber("order"));
        return bpp;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.enableAsync = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableHzhAsync.class.getName(), false));
        if (this.enableAsync == null) {
            throw new IllegalArgumentException(
                    "@EnableAsync is not present on importing class " + importMetadata.getClassName());
        }
    }

}
