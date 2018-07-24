package com.yunbao.autosendmq.Mq.DestinationCreate;

import com.rabbitmq.client.AMQP;
import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
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
        String exchangeName =  autoSendMq.exchangeNmae();
        if(exchangeName != null ){
            //创建exchange
            try {
                AMQP.Exchange.DeclareOk isok =   connectionFactory.createConnection().createChannel(false).exchangeDeclare(exchangeName, ExchangeTypes.TOPIC);
                log.debug("create exchange" + exchangeName);
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
