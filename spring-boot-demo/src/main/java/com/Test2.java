package com;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
public class Test2 implements Ordered {
    public Test2(){
        System.out.println("bbbbbbbbbbbbbb");
    }


    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
