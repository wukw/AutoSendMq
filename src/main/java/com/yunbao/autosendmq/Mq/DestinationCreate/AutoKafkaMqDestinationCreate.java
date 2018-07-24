package com.yunbao.autosendmq.Mq.DestinationCreate;

import com.yunbao.autosendmq.Annotation.AutoSendKafkaMq;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;



@Component
public class AutoKafkaMqDestinationCreate extends AutoMqDestinationCreateFactory {
    ApplicationContext applicationContext ;

    @Override
    public void process(Annotation annotation) {
        if(annotation instanceof AutoSendKafkaMq){
        }


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = this.applicationContext;
    }
}
