package com.wzy.server.master.zookeeper.watch;

import com.wzy.config.Config;
import com.wzy.server.master.RegionServer;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ApiWatch implements Watcher{

    String path;
    ZooKeeper zooKeeper;
    RegionServer regionServer;
    public ApiWatch(String path, ZooKeeper zooKeeper, RegionServer regionServer){
        this.path = path;
        this.zooKeeper = zooKeeper;
        this.regionServer = regionServer;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getType());
        regionServer.apiTroggering();

        // 执行完毕之后重新载入触发器
        try {
            zooKeeper.getChildren(path, this);
        } catch (KeeperException e) {
            Config.log.error(e);
        } catch (InterruptedException e) {
            Config.log.error(e);
        }
    }
}
