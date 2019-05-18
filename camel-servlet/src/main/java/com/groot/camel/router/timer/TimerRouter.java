package com.groot.camel.router.timer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRouter extends RouteBuilder {

    /**
     * 自动执行
     * @throws Exception
     */
    public void configure() throws Exception {

        from("timer:hello?period=10000")
                .log("hello timer: "+Thread.currentThread().getName());
    }
}
