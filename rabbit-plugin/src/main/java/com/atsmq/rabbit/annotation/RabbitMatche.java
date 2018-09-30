package com.atsmq.rabbit.annotation;

import com.atsmq.annotation.AnnotationMatchInterface;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
@Component
public class RabbitMatche implements AnnotationMatchInterface<AutoSendRabbitMq> {

    @Override
    public boolean match(Annotation annotation) {
      return  annotation.getClass() .equals(getSupport())?true:false;
    }
    @Override
    public Class getSupport() {
       return AutoSendRabbitMq.class;
    }
}
