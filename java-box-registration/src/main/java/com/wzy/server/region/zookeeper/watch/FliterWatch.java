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

public class FliterWatch implements Watcher {
    static ILoadJar loadJar = LoadJarImpl.getInstance();
    ILog log = BoxLog.getInstance();
    static Map<String,Object> watcherData = new HashMap<>();
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
                List<String> strings = zooKeeper.getChildren(watchedEvent.getPath(),false);
                strings.forEach(str ->{
                   initWater(str,zooKeeper);
                });
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            zooKeeper.getChildren(path, this);
        } catch (KeeperException e) {
            log.error(e);
        } catch (InterruptedException e) {
            log.error(e);
        }
    }

    public static void initWater(String str, ZooKeeper zooKeeper){
        if(!watcherData.containsKey(str)){
            loadJar.initFliterHttp(str);
            watcherData.put(ZkConfig.APP_FLITER+"/"+str, new WatcherData());
            try {
                zooKeeper.getData(ZkConfig.APP_FLITER+"/"+str, new WatcherData(), null);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class WatcherData implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                watcherData.remove(watchedEvent.getPath());
                String id = watchedEvent.getPath().substring(watchedEvent.getPath().lastIndexOf("/")+1, watchedEvent.getPath().length());
                loadJar.removeFliterHttp(id);
            }
        }
    }
}
