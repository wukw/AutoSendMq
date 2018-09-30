package com.atsmq.mq_extend;

import com.atsmq.annotation.AnnotationMatchInterface;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

public abstract  class BaseMqDestination<A extends  Annotation,K extends   MqConnectionInterface> implements  MqDestinationInterface<A> {
    @Autowired
    public AnnotationMatchInterface<A> autosendAnnotationMatchInterface;

    @Autowired
    public BaseConnection<A,AbstractConnectionFactory> mqConnectionInterface;

    @Override
    public abstract boolean createDestination(A a) ;

    public  boolean createDestination0(A a, K k){
        if(autosendAnnotationMatchInterface.match(a)){
            return this.createDestination(a);
        }
        return false;
    }

}
