package com.groot.camel.router.file;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileChooseRouter extends RouteBuilder {

    /**
     * file:
     * 发送消息到一个文件或者拉去一个文件或者目录
     *
     * noop: 等待无操作
     * Sending messages to a file or polling a file or directory
     * 不能有两个router同时指向同一个目录
     * xpath：
     *
     * @throws Exception
     */
    public void configure() throws Exception {
//        from("file:D:/tmp/inbox?noop=true")
//                .choice()
//                    .when(xpath("/city = 'London'")).to("file:D:/tmp/outbox/uk")
//                    .otherwise().to("file:D:/tmp/outbox/others");
    }
}
