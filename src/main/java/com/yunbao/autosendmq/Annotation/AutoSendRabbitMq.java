package com.yunbao.autosendmq.Annotation;


import org.springframework.amqp.core.ExchangeTypes;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoSendRabbitMq {
    String[]  exchangeNmae () ;

    String exchangeType() default ExchangeTypes.TOPIC;

    String routingKey() default  "#";

    long delayTime() default   0;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    boolean sync() default  true;


}
