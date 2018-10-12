package com.atsmq.rabbit.mq_rabbit;

import com.atsmq.mq_extend.BaseConnection;
import com.atsmq.rabbit.annotation.AutoSendRabbitMq;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RabbitConnection extends BaseConnection<AutoSendRabbitMq,AbstractConnectionFactory> {



    @Autowired
    ApplicationContext applicationContext;

    @Override
    public AbstractConnectionFactory createConnectionObject() {
       return  c = applicationContext.getBean(AbstractConnectionFactory.class);
    }

    @Override
    public AbstractConnectionFactory returnConnectionObject() {
        return c;
    }
}
