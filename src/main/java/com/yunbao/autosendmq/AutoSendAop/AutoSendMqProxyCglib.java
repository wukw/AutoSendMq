package com.yunbao.autosendmq.AutoSendAop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * bean 代理
 */
public class AutoSendMqProxyCglib implements MethodInterceptor {

    Object target;
    ApplicationContext applicationContext ;
    AutoSendProxy autoSendProxy;

    public Object getInstance(Object targetObject, ApplicationContext applicationContext ) {
        this.target = targetObject;
        this.autoSendProxy = applicationContext.getBean(AutoSendProxy.class);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib代理"+method.toString());
        Object resultObj = proxy.invokeSuper(obj, args);
        autoSendProxy.autoSendRabbit(method,resultObj);
        return resultObj;
    }

}
