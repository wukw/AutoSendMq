package com.yunbao.autosendmq.Mq.SendTemplate;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component(TemplateConfig.RabbitDefaultReturnCallbackName)
public class RabbitReturn implements RabbitTemplate.ReturnCallback {
    String a = "a";

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("发送失败");
    }


}
