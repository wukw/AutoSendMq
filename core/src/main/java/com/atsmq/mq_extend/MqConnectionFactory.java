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

    static Map map = new HashMap();
    public static <K,V>  V instance(K k){
        return(V)map.get(k);
    }
    public static <K>  boolean hasKey(K k) {
        return map.containsKey(k);
    }
    public static <K,V>  void register(K k, V v) {
        map.put(k, v);
    }
}
