package com.yunbao.autosendmq.Mq.SendTemplate;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component(TemplateConfig.RabbitDefalutConfirmCallbackName)
public class RabbitConfirm implements RabbitTemplate.ConfirmCallback {



    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("CorrelationData"+correlationData.getId()+ack);
    }
}
