package com.yunbao.autosendmq.AnnotationBean;

import com.yunbao.autosendmq.AutoSendAop.AutoSendMqProxyCglib;
import com.yunbao.autosendmq.Mq.AutoMqTemplateProcessFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 扫描bean
 */
@Component
public class AutoSendMqAnnitationBean implements BeanFactoryPostProcessor ,DisposableBean,BeanPostProcessor,ApplicationContextAware {


    ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void destroy() throws Exception {

    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("创建bean后置"+beanName);
        Method[] methods = bean.getClass().getMethods();
        for(Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            int count = AutoMqTemplateProcessFactory.execute(annotations, applicationContext);
            if (count > 0) {
                return new AutoSendMqProxyCglib().getInstance(bean, applicationContext);
            }
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }







}
