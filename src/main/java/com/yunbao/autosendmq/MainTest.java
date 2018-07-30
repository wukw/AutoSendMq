package com.yunbao.autosendmq;

import com.yunbao.autosendmq.Test.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan()
@Configuration
@EnableAspectJAutoProxy
public class MainTest {




    public static void main(String[] args) {
//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
//        annotationConfigApplicationContext.register(AutoSendConfigurer.class);
//        annotationConfigApplicationContext.refresh();
//        Test injectClass = (Test)annotationConfigApplicationContext.getBean(Test.class);
//        injectClass.print();

        AnnotationConfigApplicationContext  context = new   AnnotationConfigApplicationContext();
        context.register(MainTest.class);
        context.refresh();
        Test test =context.getBean(Test.class);
        test.print();

    }











}
