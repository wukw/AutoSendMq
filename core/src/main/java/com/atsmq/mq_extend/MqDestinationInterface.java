package com.atsmq.mq_extend;

import java.lang.annotation.Annotation;

/**
 * Mq目的地接口
 */
public interface MqDestinationInterface<A extends Annotation>  {

    public  boolean createDestination(A a);




}
