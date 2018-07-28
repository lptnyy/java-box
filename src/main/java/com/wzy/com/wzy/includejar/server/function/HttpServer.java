package com.wzy.com.wzy.includejar.server.function;

/**
 * 定义服务的接口
 */
public interface HttpServer {

    /**
     * 服务启动初始化
     */
    void init();

    /**
     * 关闭服务
     */
    void close();
}
