package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.SendRunable;

import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.AutoSendFactory;

import java.util.concurrent.TimeUnit;

public class ActiveSendMqRunable extends   SendMqRunableInterFace {


    public ActiveSendMqRunable(AutoSendFactory autoSendFactory, String destinationName, Object object, boolean isSync, TimeUnit timeUnit, Long times) {
        this.autoSendFactory = autoSendFactory;
        this.destinationName = destinationName;
        this.object = object;
        this.timeUnit = timeUnit;
        this.times = times;
        this.isSync = isSync;
    }


    @Override
    public void run() {
        autoSendFactory.sendMq(destinationName,object,routingKey);
    }
}
