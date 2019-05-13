package com.groot.camel.rest;

import org.springframework.stereotype.Component;

@Component
public class BizService {


    public void m1(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1");
    }

    public void m2() {
        System.out.println("m2");
    }


    public void m3(){
        System.out.println("m3");
    }
}
