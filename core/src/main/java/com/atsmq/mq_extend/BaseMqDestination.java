package com.atsmq.mq_extend;

import com.atsmq.annotation.AnnotationMatchInterface;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import java.lang.annotation.Annotation;
public abstract  class BaseMqDestination<A extends  Annotation,K extends   MqConnectionInterface,C> implements InitializingBean,  MqDestinationInterface<A>  ,Ordered {
    @Autowired
    public AnnotationMatchInterface<A> autosendAnnotationMatchInterface;

    @Autowired
    public BaseConnection<A,C> mqConnectionInterface;
    @Override
    public abstract boolean createDestination(A a) ;

    public  boolean createDestination0(A a, K k){
        if(autosendAnnotationMatchInterface.match(a)){
            return this.createDestination(a);
        }
        return false;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MqDestinationCreaterFactory.register(autosendAnnotationMatchInterface.getSupport(),this );
    }
}
