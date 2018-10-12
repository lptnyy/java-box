package com.wzy.util.zookeeper;

import com.wzy.entity.BoxApp;
import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import java.io.IOException;

@Configuration
@PropertySources({
        @PropertySource("classpath:zk.properties"),
        @PropertySource(value = "${zk.properties}", ignoreResourceNotFound = true)})
public class ZookeeperUtil {
    @Value("${zkIp}")
    String zookeeperIp;
    @Value("${zkPort}")
    String zookeeperPort;
    @Value("${timeOut}")
    String timeOut;

    static ZooKeeper zooKeeper = null;

    @Bean
    public boolean init(){
        System.out.println("初始化zookeeper");
        try {
            synchronized (this) {
                if (zooKeeper == null) {
                    zooKeeper = new ZooKeeper(zookeeperIp + ":"
                            + zookeeperPort, Integer.valueOf(timeOut), new Watcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) {
                            System.out.println("zookeeper 初始化成功");
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * zookeeper 添加一个应用信息
     * @param appId
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void addAppNode(Integer appId) throws KeeperException, InterruptedException {
        String appNode = ZkConfig.APP_NODE;

        // 创建App 根节点
        if (zooKeeper.exists(appNode,false) == null) {
            zooKeeper.create(appNode,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 插入appNode信息
        String appPath=appNode+"/"+appId;
        if (zooKeeper.exists(appPath,false) == null) {
            zooKeeper.create(appPath,appId.toString().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    public void deleteAppNode(Integer appId) throws KeeperException, InterruptedException {
        String appNode=ZkConfig.APP_NODE+"/"+appId;
        if (zooKeeper.exists(appNode,false) != null) {
            zooKeeper.delete(appNode, -1);
        }
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }
}
