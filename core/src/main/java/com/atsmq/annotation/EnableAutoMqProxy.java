package com.atsmq.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableAutoMqProxy {
}
