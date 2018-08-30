package com.wzy.server.region.zookeeper.watch;

import com.wzy.server.config.Config;
import com.wzy.server.region.RegionServer;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class MoudularWatch implements Watcher {

    String path;
    ZooKeeper zooKeeper;
    RegionServer regionServer;
    public MoudularWatch(String path, ZooKeeper zooKeeper, RegionServer regionServer){
        this.path = path;
        this.zooKeeper = zooKeeper;
        this.regionServer = regionServer;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getType());
        regionServer.moudularTroggering(watchedEvent);

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
