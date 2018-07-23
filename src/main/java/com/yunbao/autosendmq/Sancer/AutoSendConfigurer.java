//package com.yunbao.autosendmq.Sancer;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//@Component
//@Configuration
//public class AutoSendConfigurer implements BeanFactoryPostProcessor, ApplicationContextAware {
//
//    private ApplicationContext applicationContext;
//
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        AutoSendSancer scanner = new AutoSendSancer((BeanDefinitionRegistry) beanFactory);
//        scanner.setResourceLoader(this.applicationContext);
//        scanner.scan("com.yunbao.autosendmq.Test");
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}
