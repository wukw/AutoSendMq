package com.atsmq.proxy;

import com.atsmq.annotation.AnnotationMatchInterface;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

/**
 *
 * @param <A> 匹配注解
 * @param <T> 实际模板
 */
public abstract  class BaseSendTemplate<A extends Annotation,T> implements MqSendTemplateInterface<A,T> ,InitializingBean{

    boolean initTemplateIsSuccess = false;

    T t;

    @Autowired
    public AnnotationMatchInterface<A> annotationMatchInterface;

    public boolean createSendTemplate0(){
        t = createSendTemplate();
        initTemplateIsSuccess = t != null?true:false;
        return initTemplateIsSuccess;
    }

    @Override
    public void autoSendMq(A annotation, Object result) {
        if(annotationMatchInterface.match(annotation)){
            doTemplateSend(annotation, result);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       if(createSendTemplate0()) {
           MqSendtemplateFactory.register(annotationMatchInterface.getSupport(), this);
       }
    }

    @Override
    public T getTemplate() {
        return t;
    }
}
