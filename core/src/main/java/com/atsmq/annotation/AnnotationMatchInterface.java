package com.atsmq.annotation;

import java.lang.annotation.Annotation;

public interface AnnotationMatchInterface<A extends  Annotation> {

    public boolean match(Annotation  a);


    public Class getSupport();
}
