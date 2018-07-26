package com.yunbao.autosendmq.AutoSendAop.AutoSendProxy;

import com.yunbao.autosendmq.Annotation.AutoSendActiveMq;
import com.yunbao.autosendmq.Annotation.AutoSendKafkaMq;
import com.yunbao.autosendmq.Annotation.AutoSendRabbitMq;
import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.SendRunable.SendMqRunableInterFace;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public abstract class AutoSendFactory implements ApplicationListener<ContextRefreshedEvent> {

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(64);

    public static List<AutoSendFactory> chooseAutoSendMq(Method method, ApplicationContext applicationContext){
        List<AutoSendFactory> choosedMq = new ArrayList<>();
        Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof AutoSendRabbitMq){
                AutoSendRabbitProxy autoSendProxy =  applicationContext.getBean(AutoSendRabbitProxy.class);
                choosedMq.add(autoSendProxy);
            }
            if(annotation instanceof AutoSendActiveMq){
                AutoSendActiveProxy autoSendProxy =  applicationContext.getBean(AutoSendActiveProxy.class);
                choosedMq.add(autoSendProxy);
            }
            if(annotation instanceof AutoSendKafkaMq){
                AutoSendKafkaProxy autoSendProxy =  applicationContext.getBean(AutoSendKafkaProxy.class);
                choosedMq.add(autoSendProxy);
            }
        }
        return choosedMq;
    }




    public abstract Object autoSendMq(Method method,Object object);

    public abstract void sendMq(String[] destinationName,Object obj,String routingKey);

    /**
     * 发送任务
     * @param sendMqRunableInterFace
     */
    public void sendRunable(SendMqRunableInterFace sendMqRunableInterFace){
        if(sendMqRunableInterFace.isSync()){
            try {
                //延迟发送
                sendMqRunableInterFace.getTimeUnit().sleep(sendMqRunableInterFace.getTimes());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendMq(sendMqRunableInterFace.getDestinationName(),sendMqRunableInterFace.getObject(),sendMqRunableInterFace.getRoutingKey());
        }else{
            executor.schedule(sendMqRunableInterFace,sendMqRunableInterFace.getTimes(),sendMqRunableInterFace.getTimeUnit());

        }
    }










}
