package com.yunbao.autosendmq.Mq.DestinationCreate;

import com.rabbitmq.client.AMQP;
import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@Slf4j
public class AutoRabbitMqDestinationCreate extends AutoMqDestinationCreateFactory {
    ApplicationContext applicationContext ;



    @Override
    public void process(Annotation annotation) {
        AbstractConnectionFactory connectionFactory = null;
        try {
             connectionFactory = applicationContext.getBean(AbstractConnectionFactory.class);
        }catch (Exception e){
            log.warn("no rabbit connectionFactory");
            return ;
        }
        AutoSendRabbitMq autoSendMq = (AutoSendRabbitMq) annotation;
        String[] exchangeNameS =  autoSendMq.exchangeNmae();
        if(exchangeNameS.length > 0){
            //创建exchange
            try {
                for(String nameTemp : exchangeNameS) {
                    connectionFactory.createConnection().createChannel(false).exchangeDeclare(nameTemp, autoSendMq.exchangeType());
                    log.debug("create exchange" + nameTemp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
