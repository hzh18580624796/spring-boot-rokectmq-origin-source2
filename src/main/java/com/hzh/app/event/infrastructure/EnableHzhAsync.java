

package com.hzh.app.event.infrastructure;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HzhAsyncConfigurationSelector.class)
public @interface EnableHzhAsync {


    Class<? extends Annotation> annotation() default Annotation.class;


    boolean proxyTargetClass() default false;


    AdviceMode mode() default AdviceMode.PROXY;


    int order() default Ordered.LOWEST_PRECEDENCE;

}
