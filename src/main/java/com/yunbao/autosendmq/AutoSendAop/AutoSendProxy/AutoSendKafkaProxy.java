package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.AutoSendFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
public class AutoSendKafkaProxy extends AutoSendFactory {

   @Autowired
   ApplicationContext applicationContext;
    public Object autoSendMq(Method method,Object object){
        
        return object;

    }

}
