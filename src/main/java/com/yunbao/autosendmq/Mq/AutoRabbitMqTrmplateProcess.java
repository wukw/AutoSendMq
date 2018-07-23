package com.yunbao.autosendmq.Mq;

import com.rabbitmq.client.AMQP;
import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class AutoRabbitMqTrmplateProcess extends AutoMqTemplateProcessFactory {
    ApplicationContext applicationContext ;



    @Override
    public void process(Annotation annotation) {
        System.out.println("创建exchage");
        AbstractConnectionFactory connectionFactory = null;
        try {
             connectionFactory = applicationContext.getBean(AbstractConnectionFactory.class);
        }catch (Exception e){
            return ;
        }
        AutoSendRabbitMq autoSendMq = (AutoSendRabbitMq) annotation;
        String exchangeName =  autoSendMq.exchangeNmae();
        if(exchangeName != null ){
            //创建exchange
            System.out.println("获取到exchange"+exchangeName);
            try {
                AMQP.Exchange.DeclareOk isok =   connectionFactory.createConnection().createChannel(false).exchangeDeclare(exchangeName, ExchangeTypes.TOPIC);
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
