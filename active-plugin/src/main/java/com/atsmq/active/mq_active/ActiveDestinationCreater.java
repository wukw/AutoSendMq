package com.atsmq.active.mq_active;

import com.atsmq.active.annotation.AutoSendActiveMq;
import com.atsmq.mq_extend.BaseMqDestination;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class ActiveDestinationCreater extends BaseMqDestination<AutoSendActiveMq,ActiveConnection,Connection> {

    @Override
    public boolean createDestination(AutoSendActiveMq autoSendActiveMq) {
        Connection connection =  mqConnectionInterface.returnConnectionObject();
        try {
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = null;
            String[] queueNames  = autoSendActiveMq.queueName();
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
            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }
}
