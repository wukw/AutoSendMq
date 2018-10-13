package com.atsmq.annotation_bean;

import com.atsmq.annotation.EnableAutoMqProxy;
import com.atsmq.mq_extend.MqDestinationCreaterFactory;
import com.atsmq.proxy.AutoSendMqProxyCglib;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * 扫描bean
 */
@Component
public class AutoSendMqAnnitationBean implements BeanFactoryPostProcessor,  DisposableBean,BeanPostProcessor,ApplicationContextAware {


    ApplicationContext applicationContext;
    @Override
    public void destroy() throws Exception {
        System.out.println("结束创建");

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Annotation[] annotations = bean.getClass().getAnnotations();
        System.out.println(beanName);
        for(Annotation annotation : annotations){
            //有注解的创建代理类
            if(annotation instanceof EnableAutoMqProxy){
                //保存class信息用于之后的mq的目的地创建
                MqDestinationCreaterFactory.putTragetClazz(bean.getClass());
                return new AutoSendMqProxyCglib().getInstance(bean, applicationContext);
            }
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("xxx");

    }
}
