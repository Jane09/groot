package com.groot.knowledge.http;

public class MessageHandler implements IMessageHandler {


    public <M, R> R send(M message) throws Exception {
        FilterChain chain = new FilterChain();
        //根据不同渠道进行不同处理
        Object rsp = chain.process(message,chain,true);
        return (R) message;
    }
}
