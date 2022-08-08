package com.hzh.app.circulardependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HzhClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

    public HzhClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation}, true, null);
    }

    public HzhClassPathXmlApplicationContext(
            String[] configLocations, boolean refresh, ApplicationContext parent)
            throws BeansException {

        super(parent);
        setConfigLocations(configLocations);
        if (refresh) {
            refresh();
        }
    }

    @Override
    public void refresh() throws BeansException, IllegalStateException {
        //synchronized (this.startupShutdownMonitor) {


            // Prepare this context for refreshing.
            prepareRefresh();

            // Tell the subclass to refresh the internal bean factory.
            ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

            // Prepare the bean factory for use in this context.
            prepareBeanFactory(beanFactory);

            try {
                // Allows post-processing of the bean factory in context subclasses.
                postProcessBeanFactory(beanFactory);


                //fixme start
                //fixme 这里是定制的，必须要先添加这个，否则BeanFactoryPostProcessor实现类无法完成自动注入，
                //fixme 因为BeanFactoryPostProcessor创建bean的时候，还没有AutowiredAnnotationBeanPostProcessor CommonAnnotationBeanPostProcessor（这两个是完成自动注入的关键）
                AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
                autowiredAnnotationBeanPostProcessor.setBeanFactory(beanFactory);

                beanFactory.addBeanPostProcessor(autowiredAnnotationBeanPostProcessor);
                //fixme end


                // Invoke factory processors registered as beans in the context.
                invokeBeanFactoryPostProcessors(beanFactory);



                // Register bean processors that intercept bean creation.
                registerBeanPostProcessors(beanFactory);

                // Initialize message source for this context.
                initMessageSource();

                // Initialize event multicaster for this context.
                initApplicationEventMulticaster();

                // Initialize other special beans in specific context subclasses.
                onRefresh();

                // Check for listener beans and register them.
                registerListeners();

                // Instantiate all remaining (non-lazy-init) singletons.
                finishBeanFactoryInitialization(beanFactory);

                // Last step: publish corresponding event.
                finishRefresh();
            } catch (BeansException ex) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Exception encountered during context initialization - " +
                            "cancelling refresh attempt: " + ex);
                }

                // Destroy already created singletons to avoid dangling resources.
                destroyBeans();

                // Reset 'active' flag.
                cancelRefresh(ex);

                // Propagate exception to caller.
                throw ex;
            } finally {
                // Reset common introspection caches in Spring's core, since we
                // might not ever need metadata for singleton beans anymore...
                resetCommonCaches();
            }


        //}
    }
}
