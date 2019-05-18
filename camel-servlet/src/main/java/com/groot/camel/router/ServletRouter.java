package com.groot.camel.router;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMessage;
import org.springframework.stereotype.Component;

@Component
public class ServletRouter extends RouteBuilder {
    public void configure() throws Exception {
        from("servlet:hello")
                .routeId("hello")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getFromRouteId());
                    }
                })
                .transform().simple("hello world");
        from("servlet:getUserById")
                .routeId("getUserById")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        HttpMessage message = (HttpMessage)exchange.getIn();
                        System.out.println(JSON.toJSONString(message.getRequest().getParameterMap()));
                    }
                }).log("getUserById")
                    .transform().simple(JSON.toJSONString(new Response(200,"hello servlet")));
    }

    @AllArgsConstructor
    @Getter
    public class Response {
        private int status = 200;
        private Object data;
    }
}
