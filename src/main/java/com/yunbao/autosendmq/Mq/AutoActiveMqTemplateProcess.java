package com.yunbao.autosendmq.Mq;

import com.yunbao.autosendmq.Annotation.AutoSendActiveMq;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.Session;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * activetemplate创建
 */
@Component
public class AutoActiveMqTemplateProcess extends AutoMqTemplateProcessFactory {
    ApplicationContext applicationContext ;

    @Override
    public void process(Annotation annotation) {
        AutoSendActiveMq autoSendActiveMq = (AutoSendActiveMq) annotation;

        ActiveMQConnectionFactory activeMQConnectionFactory = null;
        try {
            activeMQConnectionFactory =   applicationContext.getBean(ActiveMQConnectionFactory.class);
            Connection connection =activeMQConnectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            session.createQueue(autoSendActiveMq.queueNmae());
            connection.close();
        }catch (Exception e){
            return ;
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = this.applicationContext;
    }
}
