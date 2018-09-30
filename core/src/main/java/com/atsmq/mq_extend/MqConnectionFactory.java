package com.atsmq.mq_extend;

import com.atsmq.annotation.BaseAnnotationFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * 注解 和 创建者 会在这里映射
 */
@Component
public class MqConnectionFactory extends BaseAnnotationFactory<Class<Annotation>,Object> {

}
