package com.atsmq.active.annotation;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoSendActiveMq {
    String[]  queueName() ;

    QueueType queueType() default QueueType.queue;

    String routingKey() default  "*";

    boolean sync() default  true;


    public enum QueueType{
        topic,queue;

    }
}
