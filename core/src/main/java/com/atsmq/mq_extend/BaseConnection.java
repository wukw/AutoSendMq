package com.atsmq.mq_extend;

import com.atsmq.annotation.AnnotationMatchInterface;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
public abstract class BaseConnection<A extends Annotation,C> implements  MqConnectionInterface,InitializingBean {

    public C c;

    @Autowired
    AnnotationMatchInterface<A> annotationMatchInterface;

    @Override
    public abstract C createConnectionObject() ;

    @Override
    public  C returnConnectionObject(){
        return c;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        c = createConnectionObject();
        MqConnectionFactory.register(annotationMatchInterface.getSupport(), c);
    }
}
