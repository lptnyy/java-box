package com.wzy.server;

import com.wzy.func.fc.IHttpServer;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.config.Config;
import com.wzy.server.netty.IHttpServerImpl;
import com.wzy.server.region.ServerNode;
import com.wzy.server.region.zookeeper.ZkNetConfig;
import com.wzy.server.region.zookeeper.ZkServer;

public class StartServer {
    static ILog log = BoxLog.getInstance();
    public static ZkNetConfig zkNetConfig;

    public static void main(String[] args) throws Exception {
        // 初始化 配置文件
        if (Config.initConfig()) {
            ServerNode node = new ServerNode();
            node.serverType = 1;
            ZkServer zkServer = new ZkServer();
            zkServer.regionService();
            while (zkServer.isConnect()) {
                log.info("load zk");
                Thread.sleep(1000);
            }
            zkNetConfig = new ZkNetConfig();
            zkNetConfig.setZkServer(zkServer);
            Config.iConfig = zkNetConfig;
            Config.initZkConfig(zkNetConfig);
            zkServer.initNode(node);

            // 启动服务
            IHttpServer IHttpServer = new IHttpServerImpl();
            IHttpServer.init();
        } else {
            log.error("启动服务失败");
        }
    }
}
