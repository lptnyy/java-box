package com.wzy.util.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
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

    ZooKeeper zooKeeper = null;

    @Bean
    public boolean init(){
        System.out.println("初始化zookeeper");
        try {
            zooKeeper = new ZooKeeper(zookeeperIp + ":"
                    + zookeeperPort, Integer.valueOf(timeOut), new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                   System.out.println("zookeeper 初始化成功");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }
}
