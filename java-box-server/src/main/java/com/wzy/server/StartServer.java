package com.wzy.server;

import com.wzy.func.fc.HttpServer;
import com.wzy.func.fc.IConfig;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.config.Config;
import com.wzy.server.netty.HttpServerImpl;
import com.wzy.server.region.ServerNode;
import com.wzy.server.region.zookeeper.ZkServer;

public class StartServer {
    static ILog log = BoxLog.getInstance();
    public static ZkServer zkServer;

    public static void main(String[] args) throws Exception {
        // 初始化 配置文件
        if (Config.initConfig()) {
            ServerNode node = new ServerNode();
            node.serverType = 1;
            zkServer = new ZkServer();
            zkServer.regionService();
            while (zkServer.isConnect()) {
                log.info("load zk");
                Thread.sleep(1000);
            }
            Config.initZkConfig(zkServer);
            zkServer.initNode(node);

            // 启动服务
            HttpServer httpServer = new HttpServerImpl();
            httpServer.init();
        } else {
            log.error("启动服务失败");
        }
    }
}
