package com.atsmq.annotation_bean;

import com.atsmq.mq_extend.MqDestinationCreaterFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 加载完成后创建mq 目的地
 */
@Component
public class CreateDestination  implements ApplicationListener<ContextRefreshedEvent>{
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MqDestinationCreaterFactory.processTragetClazz();
    }
}
