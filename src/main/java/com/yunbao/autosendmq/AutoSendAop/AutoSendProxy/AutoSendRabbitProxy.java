package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.*;


@Component
@Slf4j
public class AutoSendRabbitProxy extends AutoSendFactory {

   @Autowired
   ApplicationContext applicationContext;

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(64);

    @Override
    public Object autoSendMq(Method method,Object object){
        AutoSendRabbitMq autoSendRabbitMq = method.getAnnotation(AutoSendRabbitMq.class);
        if(autoSendRabbitMq != null && object != null){
            RabbitTemplate rabbitTemplate = null;
            try {
                rabbitTemplate = (RabbitTemplate) applicationContext.getBean(RabbitTemplate.class.getName());
            }catch (Exception e){
                return object;
            }
            //异步延迟发送
            if(!autoSendRabbitMq.sync() ){
                RabbitSendRunable runable = new RabbitSendRunable(rabbitTemplate,object,autoSendRabbitMq.routingKey(),autoSendRabbitMq.exchangeNmae());
                executor.schedule(runable,autoSendRabbitMq.delayTime(),autoSendRabbitMq.timeUnit());
            }else{
                try {
                    //延迟
                    autoSendRabbitMq.timeUnit().sleep(autoSendRabbitMq.delayTime());
                } catch (InterruptedException e) {
                    log.error("method" + method.getName() + "auto mq wait Interrupted");
                    e.printStackTrace();
                }
                //自动发送
                rabbitTemplate.convertAndSend(autoSendRabbitMq.exchangeNmae(),autoSendRabbitMq.routingKey(),object);
            }
        }
        return object;

    }

}
