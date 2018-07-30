package com.yunbao.autosendmq.AutoSendAop;

import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.AutoSendFactory;
import com.yunbao.autosendmq.AutoSendAop.AutoSendProxy.AutoSendRabbitProxy;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * bean 代理
 */
@Slf4j
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
        log.info(method.getName()+" interceptd by automq  starttime"+ System.currentTimeMillis());
        Object resultObj = proxy.invokeSuper(obj, args);
        //寻找符合的mq 匹配 发送方式
        List<AutoSendFactory> autoSendMqList = AutoSendFactory.chooseAutoSendMq(method,applicationContext);
        autoSendMqList.stream().forEach(temp ->{
            temp.autoSendMq(method,resultObj);
        });
        log.info(method.getName()+" interceptd by automq  endtime"+ System.currentTimeMillis());
        return resultObj;
    }

}
