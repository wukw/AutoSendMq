package com.atsmq.active.mq_active;

import com.atsmq.active.annotation.AutoSendActiveMq;
import com.atsmq.mq_extend.BaseConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Component
public class ActiveConnection extends BaseConnection<AutoSendActiveMq,Connection> {
    @Autowired
    ApplicationContext applicationContext ;

    @Override
    public Connection createConnectionObject() {
        ConnectionFactory  activeMQConnectionFactory =   applicationContext.getBean(ActiveMQConnectionFactory.class);
        Connection connection ;
        try {
            connection =  activeMQConnectionFactory.createConnection();
            return  connection;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }


}
