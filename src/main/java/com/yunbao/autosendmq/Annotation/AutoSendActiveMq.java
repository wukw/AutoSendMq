package com.yunbao.autosendmq.Annotation;


import org.springframework.amqp.core.ExchangeTypes;

import java.lang.annotation.*;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoSendActiveMq {
    String  queueNmae() ;

    String exchangeType() default ExchangeTypes.TOPIC;

    String routingKey() default  "*";

    boolean sync() default  true;
}
