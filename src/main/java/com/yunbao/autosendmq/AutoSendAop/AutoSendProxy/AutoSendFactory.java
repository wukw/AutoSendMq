package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class AutoSendFactory {

    public static List<AutoSendFactory> chooseAutoSendMq(Method method, ApplicationContext applicationContext){
        List<AutoSendFactory> choosedMq = new ArrayList<>();
        Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof AutoSendRabbitMq){
                AutoSendRabbitProxy autoSendProxy =  applicationContext.getBean(AutoSendRabbitProxy.class);
                choosedMq.add(autoSendProxy);
            }
            if(annotation instanceof AutoSendRabbitMq){
                AutoSendActiveProxy autoSendProxy =  applicationContext.getBean(AutoSendActiveProxy.class);
                choosedMq.add(autoSendProxy);
            }
            if(annotation instanceof AutoSendRabbitMq){
                AutoSendKafkaProxy autoSendProxy =  applicationContext.getBean(AutoSendKafkaProxy.class);
                choosedMq.add(autoSendProxy);
            }
        }
        return choosedMq;
    }

    public abstract Object autoSendMq(Method method,Object object);




}
