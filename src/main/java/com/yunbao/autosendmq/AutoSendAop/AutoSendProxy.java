package com.yunbao.autosendmq.AutoSendAop;

import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
public class AutoSendProxy {

   @Autowired
   ApplicationContext applicationContext;



    //发送kafka
    public Object autoSendKafka(Method method,Object object) throws Throwable {

        return null;

    }
    //发送rabbit
    public Object autoSendRabbit(Method method,Object object) throws Throwable {
        AutoSendRabbitMq autoSendRabbitMq = method.getAnnotation(AutoSendRabbitMq.class);
        if(autoSendRabbitMq != null && object != null){
            RabbitTemplate rabbitTemplate = null;
            try {
                rabbitTemplate = (RabbitTemplate) applicationContext.getBean(RabbitTemplate.class.getName());
            }catch (Exception e){
                return object;
            }
            System.out.println("自动发送mq");
            //自动发送
            rabbitTemplate.convertSendAndReceive(autoSendRabbitMq.exchangeNmae(),autoSendRabbitMq.routringKey(),object);
        }
        return object;

    }

    //发送active
    public Object autoSendActive(Method method,Object object) throws Throwable {
        return null;

    }



}
