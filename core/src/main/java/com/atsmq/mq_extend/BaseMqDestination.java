package com.atsmq.mq_extend;

import java.lang.annotation.Annotation;

public abstract  class BaseMqDestination<T extends  Annotation> implements  MqDestinationInterface {
    public  boolean createDestination0(T t){
        if(this.matchAnnotation(t)){
            return this.createDestination(t);
        }
        return false;
    }
}
