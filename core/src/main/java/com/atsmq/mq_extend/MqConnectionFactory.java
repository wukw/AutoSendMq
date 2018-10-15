package com.atsmq.mq_extend;

import com.atsmq.annotation.BaseAnnotationFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解 和 连接mq  会在这里映射
 */
public class MqConnectionFactory extends BaseAnnotationFactory {

    static Map<Class<? extends  Annotation>,BaseConnection> map = new HashMap();
    public static BaseConnection instance(Class<? extends  Annotation> k){
        return map.get(k);
    }
    public static  boolean hasKey(Class<? extends  Annotation> k) {
        return map.containsKey(k);
    }

    public static  void register(Class<? extends  Annotation> k, BaseConnection v) {
        map.put(k, v);
    }
}
