package com.atsmq.mq_extend;

import com.atsmq.annotation.AnnotationMatchInterface;

import java.lang.annotation.Annotation;

/**
 * Mq目的地接口
 */
public interface MqDestinationInterface extends AnnotationMatchInterface {

    /**
     * 创建目的地
     * @return
     */
    public boolean createDestination(Annotation annotation);


}
