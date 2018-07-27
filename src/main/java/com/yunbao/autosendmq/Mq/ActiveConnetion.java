package com.yunbao.autosendmq.Mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ActiveConnetion extends ActiveMQConnectionFactory {

    public ActiveConnetion(){
        super();
        try {
            this.brokerURL = new URI("tcp://192.168.31.14:61616");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.userName = "admin";
        this.password = "admin";
    }
}
