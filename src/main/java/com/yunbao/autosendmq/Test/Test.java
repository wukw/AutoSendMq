package com.yunbao.autosendmq.Test;

import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import org.springframework.stereotype.Component;

@Component("com.yunbao.autosendmq.Test.Test")
public class Test {


    @AutoSendRabbitMq(exchangeNmae = "testexchange")
    public String print() {
        System.out.println("scanClass1");
        return "aaaaaaaaaaaa";
    }

    @AutoSendRabbitMq(exchangeNmae = "testexchange")
    public void print2() {
        System.out.println("scanClass2");
    }
}
