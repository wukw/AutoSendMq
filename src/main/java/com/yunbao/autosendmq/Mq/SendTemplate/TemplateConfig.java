package com.yunbao.autosendmq.Mq.SendTemplate;

import org.apache.activemq.ActiveMQConnectionFactory;
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
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

@Component
public class TemplateConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ApplicationContext applicationContext;


    /**
     * 创建rabbitTemplate
     */
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

    /**
     * 创建jmsTemplate
     */
    public void jmsActiveMessagingTemplate() {

        ConnectionFactory connectionFactory = null;
        try {
            connectionFactory = applicationContext.getBean(ActiveMQConnectionFactory.class);
        }catch (Exception e){
            return ;
        }
        if(connectionFactory != null){
            JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
            Map<String,Object> properts = new HashMap();
            properts.put("jmsTemplate",jmsTemplate);
            createSpringBean(JmsMessagingTemplate.class,properts);
        }

    }






    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        rabbitTemplate();
        jmsActiveMessagingTemplate();

    }

    /**
     * 手动注册bean
     * @param target
     * @param properts
     */
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
