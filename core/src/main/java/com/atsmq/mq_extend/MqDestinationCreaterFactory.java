package com.atsmq.mq_extend;

import com.atsmq.annotation.BaseAnnotationFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注解 和 创建者 会在这里映射
 */
@Component
public class MqDestinationCreaterFactory extends BaseAnnotationFactory<Class,BaseMqDestination> {

}
