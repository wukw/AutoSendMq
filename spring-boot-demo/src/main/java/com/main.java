package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class main {

    public static void main(String[] args) {
       ApplicationContext ac  =  SpringApplication.run(main.class,args);
       Test test = ac.getBean("test",Test.class);
       test.show();
    }
}
