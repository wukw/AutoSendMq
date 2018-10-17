package com.atsmq.proxy;

import com.atsmq.annotation.AnnotationMatchInterface;

import java.lang.annotation.Annotation;

/**
 * 发送模板接口
 */
public interface MqSendTemplateInterface<A,T>  {

    public void autoSendMq(A annotation,Object result);

    /**
     * 调用模板类发送消息
     * @param annotation
     * @param result
     */
    public void doTemplateSend(A annotation,Object result);

    /**
     * 创建 发送模板对象
     */
    public T createSendTemplate();

    public T getTemplate();




}
