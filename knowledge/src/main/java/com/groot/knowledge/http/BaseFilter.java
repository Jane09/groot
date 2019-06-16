package com.groot.knowledge.http;

public interface BaseFilter {

    <R, M> R process(M m,FilterChain filterChain,boolean processflag) throws Exception;
}
