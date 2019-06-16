package com.groot.knowledge.http;

public class HttpFilter implements BaseFilter {
    @Override
    public <R, M> R process(M m, FilterChain filterChain, boolean processflag) throws Exception {
        //TODO调用远程请求
        return filterChain.process(m,filterChain,processflag);
    }
}
