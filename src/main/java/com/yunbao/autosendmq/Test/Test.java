package com.yunbao.autosendmq.Test;

import com.yunbao.autosendmq.Annotation.AutoSendActiveMq;
import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import org.springframework.stereotype.Component;

@Component("com.yunbao.autosendmq.Test.Test")
public class Test {


    @AutoSendRabbitMq(exchangeNmae = "testexchange")
    @AutoSendActiveMq(queueNmae = "testautotopic",queueType = AutoSendActiveMq.QueueType.topic)
    public String print() {
        return "aaaaaaaaaaaa";
    }

    @AutoSendRabbitMq(exchangeNmae = "testexchange")
    public void print2() {
        System.out.println("scanClass2");
    }
}
