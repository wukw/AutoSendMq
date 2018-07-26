package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
public class AutoSendKafkaProxy extends AutoSendFactory {

   @Autowired
   ApplicationContext applicationContext;
   @Override
    public Object autoSendMq(Method method,Object object){
        
        return object;

    }

    @Override
    public void sendMq(String destinationName, Object obj,String routingKing) {

    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
