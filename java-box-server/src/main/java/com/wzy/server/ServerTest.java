package com.wzy.server;

import com.wzy.server.config.Config;
import com.wzy.server.region.ServerNode;
import com.wzy.server.region.zookeeper.ZkServer;

public class ServerTest {

    public static void main(String[] args) throws InterruptedException {
        Config.initConfig();
        ServerNode node = new ServerNode();
        ZkServer zkServer = new ZkServer();
        zkServer.regionService(node);
        Thread.sleep(1000 * 60 * 5);
    }
}
