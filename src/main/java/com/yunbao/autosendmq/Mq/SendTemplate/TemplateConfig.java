package com.yunbao.autosendmq.Mq.SendTemplate;

import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TemplateConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ApplicationContext applicationContext;



    public void rabbitTemplate() {
        AbstractConnectionFactory abstractConnectionFactory = null;
        try {
            abstractConnectionFactory = applicationContext.getBean(AbstractConnectionFactory.class);
        }catch (Exception e){
        }
        if (abstractConnectionFactory != null){
            Map<String,Object> properts = new HashMap();
            properts.put("connectionFactory",abstractConnectionFactory);
            createSpringBean(RabbitTemplate.class,properts);
         }
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        rabbitTemplate();

    }

    public void createSpringBean(Class target,Map<String,Object> properts){
        ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) applicationContext;
        BeanDefinitionRegistry beanDefinitionRegistry = (DefaultListableBeanFactory) configurableContext.getBeanFactory();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(target);

        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        if(properts != null) {
            Set<String> keys =properts.keySet();
            for(String key : keys){
                beanDefinitionBuilder.addPropertyValue(key,properts.get(key));
            }
        }
        beanDefinitionRegistry.registerBeanDefinition(target.getName(), beanDefinition);

    }

}
