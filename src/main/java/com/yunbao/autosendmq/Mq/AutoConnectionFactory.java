package com.yunbao.autosendmq.Mq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

@Component("AutoConnectionFactory")
public class AutoConnectionFactory extends  CachingConnectionFactory {

    public AutoConnectionFactory() {
        super();
        super.setAddresses("192.168.31.224");
        super.setPort(5671);
        super.setUsername("yunbao");
        super.setPassword("yunbao");
        super.setVirtualHost("/yunbao");
    }

}
