package com.wzy.server.region.zookeeper.watch;

import com.wzy.server.config.Config;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

public class FliterWatch implements Watcher {
    String path;
    ZooKeeper zooKeeper;
    public FliterWatch(String path, ZooKeeper zooKeeper){
        this.path = path;
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // 执行完毕之后重新载入触发器
        if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            try {
                List<String> values = zooKeeper.getChildren(watchedEvent.getPath(), false);
                try {
                        Config.loadJar.initAppHttp(values);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            zooKeeper.getChildren(path, this);
        } catch (KeeperException e) {
            Config.log.error(e);
        } catch (InterruptedException e) {
            Config.log.error(e);
        }
    }
}
