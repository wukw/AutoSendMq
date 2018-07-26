package com.yunbao.autosendmq.Annotation;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoSendActiveMq {
    String[]  queueNmae() ;

    QueueType queueType() default QueueType.queue;

    String routingKey() default  "*";

    boolean sync() default  true;

    long delayTime() default   0;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;


    public enum QueueType{
        topic,queue;

    }
}
