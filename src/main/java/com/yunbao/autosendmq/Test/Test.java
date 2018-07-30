package com.yunbao.autosendmq.Test;

import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import com.yunbao.autosendmq.MainTest;
import org.springframework.stereotype.Component;

@Component
public class Test {


    @AutoSendRabbitMq(exchangeNmae = "testexchange",routingKey = "123")
    //@AutoSendActiveMq(queueNmae = {"test1","test2"},queueType = AutoSendActiveMq.QueueType.topic)
    public MainTest print() {
        return  null;
    }

    @AutoSendRabbitMq(exchangeNmae = "testexchange")
    public void print2() {
        System.out.println("scanClass2");
    }
}
