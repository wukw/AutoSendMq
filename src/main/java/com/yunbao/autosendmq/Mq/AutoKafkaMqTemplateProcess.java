package com.yunbao.autosendmq.Mq;

import com.yunbao.autosendmq.Annotation.AutoSendKafkaMq;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;



@Component
public class AutoKafkaMqTemplateProcess extends AutoMqTemplateProcessFactory {
    ApplicationContext applicationContext ;

    @Override
    public void process(Annotation annotation) {
        if(annotation instanceof AutoSendKafkaMq){
            System.out.println("kafkamq");
        }


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = this.applicationContext;
    }
}
