package com.wzy.server.region.zookeeper.watch;

import com.wzy.func.fc.ILoadJar;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.jar.loader.LoadJarImpl;
import com.wzy.util.zookeeper.ZkConfig;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class ConfigWatch implements Watcher {
    static ILoadJar loadJar = LoadJarImpl.getInstance();
    ILog log = BoxLog.getInstance();
    static Map<String,Object> watcherData = new HashMap<>();
    String path;
    ZooKeeper zooKeeper;

    public ConfigWatch(String path, ZooKeeper zooKeeper){
        this.path = path;
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // 执行完毕之后重新载入触发器
        if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            System.out.println(watchedEvent.getPath());
        }
        try {
            zooKeeper.getChildren(path, this);
        } catch (KeeperException e) {
            log.error(e);
        } catch (InterruptedException e) {
            log.error(e);
        }
    }
}
