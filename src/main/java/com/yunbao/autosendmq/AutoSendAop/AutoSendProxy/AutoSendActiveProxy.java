package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import com.yunbao.autosendmq.Annotation.AutoSendActiveMq;
import com.yunbao.autosendmq.Annotation.AutoSendKafkaMq;
import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.SendRunable.ActiveSendMqRunable;
import com.yunbao.autosendmq.Mq.SendTemplate.TemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ScheduledThreadPoolExecutor;


@Component
@Slf4j
public class AutoSendActiveProxy extends AutoSendFactory {

   @Autowired
   ApplicationContext applicationContext;

    @Autowired
    TemplateConfig templateConfig;

    @Autowired
    AutoSendActiveProxy autoSendActiveProxy;


    JmsMessagingTemplate jmsMessagingTemplate ;


   @Override
   public Object autoSendMq(Method method,Object object){
       Object sendObj = object;
       AutoSendActiveMq autoSendActiveMq = method.getAnnotation(AutoSendActiveMq.class);
        if(autoSendActiveMq != null) {
            ActiveSendMqRunable activeSendMqRunable = new ActiveSendMqRunable(autoSendActiveProxy, autoSendActiveMq.queueNmae(), sendObj, autoSendActiveMq.sync(), autoSendActiveMq.timeUnit(), autoSendActiveMq.delayTime());
            sendRunable(activeSendMqRunable);
        }
       return object;

    }

    @Override
    public void sendMq(String[] destinationName, Object obj,String routingKey) {
       if(jmsMessagingTemplate != null) {
           for(String temp : destinationName) {
               jmsMessagingTemplate.convertAndSend(temp, obj);
           }
       }
    }






    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        JmsMessagingTemplate jmsMessagingTemplate = null;
        try {
            jmsMessagingTemplate = (JmsMessagingTemplate) applicationContext.getBean(JmsMessagingTemplate.class.getName());
        }catch (Exception e){
        }
        this.jmsMessagingTemplate = jmsMessagingTemplate;

    }
}
