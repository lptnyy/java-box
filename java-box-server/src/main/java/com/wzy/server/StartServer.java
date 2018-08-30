package com.wzy.server;

import com.wzy.server.config.Config;
import com.wzy.server.netty.HttpServer;
import com.wzy.server.netty.HttpServerImpl;
import com.wzy.server.region.ServerNode;
import com.wzy.server.region.zookeeper.ZkServer;

public class StartServer {
    
    public static void main(String[] args) throws Exception {
        Config.initConfig();
        ServerNode node = new ServerNode();
        ZkServer zkServer = new ZkServer();
        zkServer.regionService(node);
        HttpServer httpServer = new HttpServerImpl();
        httpServer.init();
    }
}
