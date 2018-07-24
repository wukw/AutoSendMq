package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitSendRunable implements Runnable {

    RabbitTemplate rabbitTemplate ;

    Object object ;

    String routingKey;

    String exchangeNmae ;

    public RabbitSendRunable(RabbitTemplate rabbitTemplate, Object object, String routingKey, String exchangeNmae) {
        this.rabbitTemplate = rabbitTemplate;
        this.object = object;
        this.routingKey = routingKey;
        this.exchangeNmae = exchangeNmae;
    }

    @Override
    public void run() {
        rabbitTemplate.convertAndSend(exchangeNmae,routingKey,object);
    }
}
