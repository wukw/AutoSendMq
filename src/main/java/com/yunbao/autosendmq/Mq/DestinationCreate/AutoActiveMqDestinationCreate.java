package com.yunbao.autosendmq.Mq.DestinationCreate;

import com.yunbao.autosendmq.Annotation.AutoSendActiveMq;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.lang.annotation.Annotation;

/**
 * activetemplate创建
 */
@Component
@Slf4j
public class AutoActiveMqDestinationCreate extends AutoMqDestinationCreateFactory {
    ApplicationContext applicationContext ;

    @Override
    public void process(Annotation annotation) {
        AutoSendActiveMq autoSendActiveMq = (AutoSendActiveMq) annotation;

        ConnectionFactory activeMQConnectionFactory = null;
        Connection connection = null;
        try {

            activeMQConnectionFactory =   applicationContext.getBean(ActiveMQConnectionFactory.class);
            if(activeMQConnectionFactory != null) {
                connection = activeMQConnectionFactory.createConnection();
                connection.start();
                Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
                Destination destination = null;
                String[] queueNames  = autoSendActiveMq.queueNmae();
                for(String nameTemp : queueNames) {
                    if (autoSendActiveMq.queueType().equals(AutoSendActiveMq.QueueType.topic)) {
                        destination = session.createTopic(nameTemp);
                    } else {
                        destination = session.createQueue(nameTemp);
                    }
                }
                // 得到消息生成者【发送者】 要不然无法发送消息
                MessageProducer producer = session.createProducer(destination);
                //持久化
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                session.commit();
            }
        }catch (Exception e){
           log.warn("application no active connectionFactory");
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
