package com.atsmq.rabbit.mq_rabbit;

import com.atsmq.mq_extend.BaseMqDestination;
import com.atsmq.rabbit.annotation.AutoSendRabbitMq;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RabbitDestinationCreater extends BaseMqDestination<AutoSendRabbitMq ,RabbitConnection,AbstractConnectionFactory> {

    @Override
    public boolean createDestination(AutoSendRabbitMq annotation) {
        AbstractConnectionFactory connectionFactory =  mqConnectionInterface.returnConnectionObject();
        String[] exchangeNameS =  annotation.exchangeNmae();
        if(exchangeNameS.length > 0){
            //创建exchange
            try {
                for(String nameTemp : exchangeNameS) {
                    connectionFactory.createConnection().createChannel(false).exchangeDeclare(nameTemp, annotation.exchangeType());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
