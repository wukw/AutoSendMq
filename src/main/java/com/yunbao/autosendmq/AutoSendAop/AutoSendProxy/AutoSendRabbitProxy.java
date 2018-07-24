package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.AutoSendFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
public class AutoSendRabbitProxy extends AutoSendFactory {

   @Autowired
   ApplicationContext applicationContext;
    public Object autoSendMq(Method method,Object object){
        AutoSendRabbitMq autoSendRabbitMq = method.getAnnotation(AutoSendRabbitMq.class);
        if(autoSendRabbitMq != null && object != null){
            RabbitTemplate rabbitTemplate = null;
            try {
                rabbitTemplate = (RabbitTemplate) applicationContext.getBean(RabbitTemplate.class.getName());
            }catch (Exception e){
                return object;
            }
            //自动发送
            rabbitTemplate.convertAndSend(autoSendRabbitMq.exchangeNmae(),autoSendRabbitMq.routringKey(),object);
        }
        return object;

    }

}
