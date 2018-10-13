package com.atsmq.active.annotation;

import com.atsmq.annotation.AnnotationMatchInterface;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
@Component
public class ActiveMatche implements AnnotationMatchInterface<AutoSendActiveMq> {
    @Override
    public boolean match(Annotation annotation) {
        return  annotation.getClass() .equals(getSupport())?true:false;
    }
    @Override
    public Class getSupport() {
        return AutoSendActiveMq.class;
    }
}
