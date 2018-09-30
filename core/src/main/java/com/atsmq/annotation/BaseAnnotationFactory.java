package com.atsmq.annotation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseAnnotationFactory<K ,V>  {
    Map<K,V>  map  = new ConcurrentHashMap<>();
    public   V instance(K k){
        return map.get(k);
    }
    public   boolean hasKey(K k) {
        return map.containsKey(k);
    }
    public   void register(K k, V v) {
        map.put(k, v);
    }
}
