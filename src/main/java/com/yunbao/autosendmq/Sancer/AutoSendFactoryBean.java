package com.yunbao.autosendmq.Sancer;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class AutoSendFactoryBean<T> implements InitializingBean, FactoryBean {
    private String innerClassName;

    public String getInnerClassName() {
        return innerClassName;
    }

    public void setInnerClassName(String innerClassName) {
        this.innerClassName = innerClassName;
    }

    @Override
    public Object getObject() throws Exception {
        System.out.println("初始化对象");
        Class innerClass = Class.forName(innerClassName);
        return innerClass.newInstance();

    }

    @Override
    public Class getObjectType() {
        try {
            return Class.forName(innerClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("创建完成后置");
    }




}
