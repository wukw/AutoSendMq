package com.atsmq.active.proxy;

import com.atsmq.active.annotation.AutoSendActiveMq;
import com.atsmq.proxy.BaseSendTemplate;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Component
public class ActiveSendTemplate extends BaseSendTemplate<AutoSendActiveMq,JmsMessagingTemplate> {
    @Override
    public void doTemplateSend(AutoSendActiveMq annotation, Object result) {
        JmsMessagingTemplate jmsMessagingTemplate = getTemplate();
        for(String s : annotation.queueName()){
            jmsMessagingTemplate.convertAndSend(s, result);
        }
    }

    @Override
    public JmsMessagingTemplate createSendTemplate() {
        ConnectionFactory connectionFactory  = applicationContext.getBean(ActiveMQConnectionFactory.class);
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate();
        jmsMessagingTemplate.setJmsTemplate(jmsTemplate);
        return jmsMessagingTemplate;
    }
}
