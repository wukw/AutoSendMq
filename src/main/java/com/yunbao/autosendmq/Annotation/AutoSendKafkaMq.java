package com.yunbao.autosendmq.Annotation;


import org.springframework.amqp.core.ExchangeTypes;

import java.lang.annotation.*;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoSendKafkaMq {
    String  exchangeNmae() ;

    String exchangeType() default ExchangeTypes.TOPIC;

    String routingKey() default  "*";
}
