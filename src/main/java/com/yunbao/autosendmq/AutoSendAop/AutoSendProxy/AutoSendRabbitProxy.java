package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.SendRunable.RabbitSendRunable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.*;


@Component
@Slf4j
public class AutoSendRabbitProxy extends AutoSendFactory {

    @Autowired
    ApplicationContext applicationContext;

    RabbitTemplate rabbitTemplate;

    @Autowired
    AutoSendRabbitProxy autoSendRabbitProxy;



    @Override
    public Object autoSendMq(Method method,Object object){
        AutoSendRabbitMq autoSendRabbitMq = method.getAnnotation(AutoSendRabbitMq.class);
        if(autoSendRabbitMq != null) {
            RabbitSendRunable runable = new RabbitSendRunable(autoSendRabbitProxy, object, autoSendRabbitMq.routingKey(), autoSendRabbitMq.exchangeNmae(), autoSendRabbitMq.sync(), autoSendRabbitMq.timeUnit(), autoSendRabbitMq.delayTime());
            sendRunable(runable);
        }
        return object;

    }

    @Override
    public void sendMq(String[] destinationName, Object obj,String routingKey) {
        if(rabbitTemplate != null) {
            for(String temp : destinationName) {
                rabbitTemplate.convertAndSend(temp, routingKey, obj);
            }
        }
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        RabbitTemplate rabbitTemplate = null;
        try {
            rabbitTemplate = (RabbitTemplate) applicationContext.getBean(RabbitTemplate.class.getName());
        }catch (Exception e){
        }
        this.rabbitTemplate = rabbitTemplate;
    }
}
