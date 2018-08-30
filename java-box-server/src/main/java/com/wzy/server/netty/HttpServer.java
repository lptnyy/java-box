package com.wzy.server.netty;

/**
 * 定义服务的接口
 */
public interface HttpServer {

    /**
     * 服务启动初始化
     */
    void init() throws Exception;

    /**
     * 关闭服务
     */
    void close() throws Exception;
}
