package com.yunbao.autosendmq.Annotation;


import org.springframework.amqp.core.ExchangeTypes;

import java.lang.annotation.*;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoSendRabbitMq {
    String  exchangeNmae () ;

    String exchangeType() default ExchangeTypes.TOPIC;

    String routringKey() default  "*";
}
