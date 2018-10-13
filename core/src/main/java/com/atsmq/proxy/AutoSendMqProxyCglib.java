package com.atsmq.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * bean 代理
 */
public class AutoSendMqProxyCglib implements MethodInterceptor {

    Object target;
    ApplicationContext applicationContext ;

    public Object getInstance(Object targetObject, ApplicationContext applicationContext ) {
        this.target = targetObject;
        this.applicationContext = applicationContext;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object resultObj = proxy.invokeSuper(obj, args);
        if(resultObj == null){
            return resultObj;
        }
        try {
           for(Annotation annotation : method.getAnnotations()) {
               if(MqSendtemplateFactory.hasKey(annotation.annotationType())){
                   BaseSendTemplate baseSendTemplate = MqSendtemplateFactory.instance(annotation.annotationType());
                   baseSendTemplate.doTemplateSend(annotation, resultObj);
               }
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultObj;
    }

}
