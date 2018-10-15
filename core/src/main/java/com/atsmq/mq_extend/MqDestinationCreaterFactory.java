package com.atsmq.mq_extend;

import com.atsmq.annotation.BaseAnnotationFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注解 和 mq目的地创建者 会在这里映射
 */
public class MqDestinationCreaterFactory extends BaseAnnotationFactory {
    static Map<Class<? extends  Annotation>,BaseMqDestination> map = new HashMap();

    static  Set<Class>  tragetClazz = new HashSet<>();

    public static void putTragetClazz(Class clazz){
        tragetClazz.add(clazz);
    }

    public  MqDestinationCreaterFactory(){
        System.out.println("初始化");
    }


    public static void  processTragetClazz(){
        for(Class clazz : tragetClazz){
           for(Method method : clazz.getMethods()){
               for(Annotation annotation : method.getAnnotations()){
                   if(hasKey(annotation.annotationType())){
                       BaseMqDestination baseMqDestination = instance(annotation.annotationType());
                       baseMqDestination.createDestination(annotation);
                   }
               }
           }
        }
    }
    public static BaseMqDestination instance(Class<? extends  Annotation> k){
        return map.get(k);
    }
    public static  boolean hasKey(Class<? extends  Annotation> k) {
        return map.containsKey(k);
    }
    public static  void register(Class<? extends  Annotation> k, BaseMqDestination v) {
        map.put(k, v);
    }



}
