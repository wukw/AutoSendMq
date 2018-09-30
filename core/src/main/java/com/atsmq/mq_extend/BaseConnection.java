package com.atsmq.mq_extend;

import com.atsmq.annotation.AnnotationMatchInterface;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

public abstract class BaseConnection<A extends Annotation,C> implements  MqConnectionInterface,InitializingBean {

    @Autowired
    MqConnectionFactory mqConnectionFactory;

    @Autowired
    AnnotationMatchInterface<A> annotationMatchInterface;

    @Override
    public abstract C createConnectionObject() ;

    @Override
    public abstract C returnConnectionObject();

    @Override
    public void afterPropertiesSet() throws Exception {
        C c = createConnectionObject();
        mqConnectionFactory.register(annotationMatchInterface.getSupport(), c);
    }
}
