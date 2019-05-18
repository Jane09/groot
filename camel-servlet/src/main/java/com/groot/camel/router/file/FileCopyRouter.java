package com.groot.camel.router.file;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileCopyRouter extends RouteBuilder {


    /**
     * 触发条件-文件夹发生变化，增量拷贝
     * @throws Exception
     */
    public void configure() throws Exception {
//        from("file:D:/tmp/inbox?noop=true")
//                .to("file:D:/tmp/outbox");
    }
}
