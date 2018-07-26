package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.SendRunable;

import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.AutoSendFactory;
import lombok.Data;

import java.util.concurrent.TimeUnit;
@Data
public abstract class SendMqRunableInterFace implements   Runnable {
    String[] destinationName ;

    Object object;

    boolean isSync;

    TimeUnit timeUnit;

    Long times;

    String routingKey;

    AutoSendFactory autoSendFactory;
}
