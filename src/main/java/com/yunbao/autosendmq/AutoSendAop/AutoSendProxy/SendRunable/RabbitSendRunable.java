package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.SendRunable;

import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.AutoSendFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.concurrent.TimeUnit;

public class RabbitSendRunable extends SendMqRunableInterFace {

    RabbitTemplate rabbitTemplate ;



    public RabbitSendRunable(AutoSendFactory autoSendFactory, Object object, String routingKey, String[] exchangeNmae, boolean isSync, TimeUnit timeUnit, Long times) {
        this.autoSendFactory = autoSendFactory;
        this.object = object;
        this.routingKey = routingKey;
        this.destinationName = exchangeNmae;
        this.timeUnit = timeUnit;
        this.times = times;
        this.isSync = isSync;
    }

    @Override
    public void run() {
        autoSendFactory.sendMq(destinationName,object,routingKey);
    }
}
