package com.atsmq.proxy;

import com.atsmq.annotation.BaseAnnotationFactory;
import com.atsmq.mq_extend.BaseMqDestination;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mq template 工厂
 */
public class MqSendtemplateFactory extends BaseAnnotationFactory {

    static Map<Class<? extends  Annotation>,BaseSendTemplate> map = new HashMap();
    public static BaseSendTemplate instance(Class<? extends  Annotation> k){
        return map.get(k);
    }
    public static  boolean hasKey(Class<? extends  Annotation> k) {
        return map.containsKey(k);
    }
    public static  void register(Class<? extends  Annotation> k, BaseSendTemplate v) {
        map.put(k, v);
    }
}
