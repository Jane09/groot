package com.groot.knowledge.http;

/**
 * 消息处理
 */
public interface IMessageHandler {

    < M, R > R send( M message) throws Exception;
}
