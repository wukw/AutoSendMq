package com.atsmq.rabbit.proxy;

import com.atsmq.proxy.BaseSendTemplate;
import com.atsmq.rabbit.annotation.AutoSendRabbitMq;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class RabbitSendTemplate extends BaseSendTemplate<AutoSendRabbitMq,RabbitTemplate> {



    @Override
    public void doTemplateSend(AutoSendRabbitMq annotation, Object result) {
        RabbitTemplate rabbitTemplate = getTemplate();
        for(String  exchange : annotation.exchangeNmae()) {
            rabbitTemplate.convertAndSend(exchange, annotation.routingKey(), result);
        }
    }
    @Override
    public RabbitTemplate createSendTemplate() {
        AbstractConnectionFactory abstractConnectionFactory = applicationContext.getBean(AbstractConnectionFactory.class);
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(abstractConnectionFactory);
        return rabbitTemplate;
    }





}
