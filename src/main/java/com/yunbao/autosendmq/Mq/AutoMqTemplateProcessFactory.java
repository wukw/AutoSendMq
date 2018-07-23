package com.yunbao.autosendmq.Mq;

import com.yunbao.autosendmq.Annotation.AutoSendActiveMq;
import com.yunbao.autosendmq.Annotation.AutoSendKafkaMq;
import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
public abstract class AutoMqTemplateProcessFactory implements  ApplicationContextAware {



    public abstract void process(Annotation annotation);

    public static Integer  execute(Annotation[] annotations,ApplicationContext applicationContext){
        int count = 0;
        for(Annotation annotation : annotations){
            AutoMqTemplateProcessFactory autoMqAnnotionProcessFactory =  getAutoMqAnnotionProcessFactory(annotation,applicationContext);
            if(autoMqAnnotionProcessFactory != null) {
                autoMqAnnotionProcessFactory.process(annotation);
                count++;
            }
        }
        return count;
    }

    public static AutoMqTemplateProcessFactory getAutoMqAnnotionProcessFactory(Annotation annotation, ApplicationContext applicationContext){
        if(annotation instanceof AutoSendRabbitMq){
            return applicationContext.getBean(AutoRabbitMqTrmplateProcess.class);
        }
        if(annotation instanceof AutoSendKafkaMq){
            return applicationContext.getBean(AutoKafkaMqTemplateProcess.class);
        }
        if(annotation instanceof AutoSendActiveMq){
            return applicationContext.getBean(AutoActiveMqTemplateProcess.class);
        }
        return null;
    }

}
