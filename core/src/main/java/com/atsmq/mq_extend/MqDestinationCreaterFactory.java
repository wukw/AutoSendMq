package com.atsmq.mq_extend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注解 和 创建者 会在这里映射
 */
public class MqDestinationCreaterFactory {
    public static Map<Class,BaseMqDestination> annotatopnCreater = new ConcurrentHashMap<>();

    public static BaseMqDestination instanceCreater(Class clazz){
       return  annotatopnCreater.get(clazz);
    }

    public static boolean hasCreater(Class clazz){
        return instanceCreater(clazz ) == null;
    }
}
