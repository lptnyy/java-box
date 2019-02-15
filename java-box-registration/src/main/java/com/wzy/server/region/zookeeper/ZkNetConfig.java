package com.wzy.server.region.zookeeper;

import com.wzy.func.fc.IConfig;
import com.wzy.util.zookeeper.ZkConfig;
import org.apache.zookeeper.KeeperException;

import java.util.List;

public class ZkNetConfig implements IConfig {

    ZkServer zkServer;

    public ZkServer getZkServer() {
        return zkServer;
    }

    public void setZkServer(ZkServer zkServer) {
        this.zkServer = zkServer;
    }

    @Override
    public List<String> keys() {
        return null;
    }

    @Override
    public List<String> values() {
        return null;
    }

    @Override
    public String getValue(String key) throws KeeperException, InterruptedException {
        String configNode = ZkConfig.APP_CONFIG+"/"+key;
        if (zkServer.getZooKeeper().exists(configNode,false) != null) {
            return new String(zkServer.getZooKeeper().getData(configNode,false,null));
        }
        return "";
    }

    @Override
    public IConfig addView(String key, String value) {
        return null;
    }
}
