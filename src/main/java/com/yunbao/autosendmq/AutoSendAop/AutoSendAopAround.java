package com.yunbao.autosendmq.AutoSendAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class AutoSendAopAround implements Ordered {
    AutoSendAopAround(){
        System.out.println("代理类");
    }


    @Around("@annotation(com.yunbao.autosendmq.Annotation.AutoSendActiveMq)")
    public  Object around(ProceedingJoinPoint point){
        try {
            System.out.println("aop 拦截");
           return  point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

    }




    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
