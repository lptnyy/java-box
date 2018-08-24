package com.wzy.server;

import com.wzy.config.Config;
import com.wzy.server.master.ServerNode;
import com.wzy.server.master.zookeeper.ZkServer;

public class ServerTest {

    public static void main(String[] args) {
        Config.initConfig();
        ServerNode node = new ServerNode();
        ZkServer zkServer = new ZkServer();
        zkServer.regionService(node);
    }
}
