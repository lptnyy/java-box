package com.wzy.server;

import com.wzy.func.fc.HttpServer;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.config.Config;
import com.wzy.server.netty.HttpServerImpl;
import com.wzy.server.region.ServerNode;
import com.wzy.server.region.zookeeper.ZkServer;

public class StartServer {
    static ILog log = BoxLog.getInstance();

    public static void main(String[] args) throws Exception {
        ServerNode node = new ServerNode();
        ZkServer zkServer = new ZkServer();
        zkServer.regionService(node);

        // 初始化 配置文件
        if (Config.initConfig(zkServer)) {
            // 启动服务
            HttpServer httpServer = new HttpServerImpl();
            httpServer.init();
        } else {
            log.error("启动服务失败");
        }
    }
}
