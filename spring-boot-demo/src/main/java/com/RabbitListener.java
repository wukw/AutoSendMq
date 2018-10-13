package com;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.adapter.AbstractAdaptableMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "ttt")
public class RabbitListener  {


    @RabbitHandler
    public void onMessage(String ms, Message message, Channel channel) throws Exception {
        System.out.println("收到消息"+ms);
        //消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        //ack返回false，并重新回到队列，api里面解释得很清楚
        //channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
    }


    @Bean
    public Binding ShopListenerActivityShopBinding() {
        return BindingBuilder.bind(ShopListenerActivityShopQueue()).to(new TopicExchange("aaaa")).with("*");
    }

    @Bean
    public Queue ShopListenerActivityShopQueue() {
        Queue queue =  new Queue("ttt",true);
        return queue;
    }



}
