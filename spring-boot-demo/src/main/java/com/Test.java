package com;


import com.atsmq.annotation.EnableAutoMqProxy;
import com.atsmq.rabbit.annotation.AutoSendRabbitMq;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
@Component
@EnableAutoMqProxy
public class Test   implements Ordered{
    public Test(){
        System.out.println("aaaaaaaa");
    }

    @AutoSendRabbitMq(exchangeNmae ="aaaa",exchangeType = ExchangeTypes.TOPIC,routingKey = "KKK")
    public String show(){
        return "aaa";
    }


    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
