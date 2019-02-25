package com.wzy.util.zookeeper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzy.server.region.ServerNode;
import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySources({
//        @PropertySource("file:${user.dir}/config/zk.properties"),
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
                            try {
                                initNode();
                            } catch (KeeperException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
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

    public void initNode() throws KeeperException, InterruptedException {
        String appNode = ZkConfig.APP_NODE;
        // 创建App 根节点
        if (zooKeeper.exists(appNode,false) == null) {
            zooKeeper.create(appNode,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 创建
        String configPath= ZkConfig.APP_CONFIG;
        if (zooKeeper.exists(configPath,false) == null) {
            zooKeeper.create(configPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        String filiters = ZkConfig.APP_FLITER;
        if (zooKeeper.exists(filiters, false) == null) {
            zooKeeper.create(filiters,"".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        String connectpool = ZkConfig.APP_CONNECT_POOL;
        if (zooKeeper.exists(connectpool, false) == null) {
            zooKeeper.create(connectpool,"".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    /**
     * zookeeper 添加一个应用信息
     * @param appId
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void addAppNode(Integer appId) throws KeeperException, InterruptedException {
        String appNode = ZkConfig.APP_NODE;

        // 插入appNode信息
        String appPath=appNode+"/"+appId;
        if (zooKeeper.exists(appPath,false) == null) {
            zooKeeper.create(appPath,appId.toString().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

    }

    public List<ServerNode> serverNodeList() throws KeeperException, InterruptedException {
        List<ServerNode> serverNodes = new ArrayList<>();
        List<String> nodes = zooKeeper.getChildren(ZkConfig.REGION_SERVER, false);
        nodes.forEach(str->{
            try {
                byte[] jsonByte = zooKeeper.getData(ZkConfig.REGION_SERVER+"/"+str,false,null);
                String json = new String(jsonByte);
                JSONObject jsonObject = JSON.parseObject(json);
                ServerNode serverNode = JSON.toJavaObject(jsonObject, ServerNode.class);
                serverNode.fileSeparator = "";
                serverNode.lineSeparator = "";
                serverNodes.add(serverNode);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return serverNodes;
    }

    public void deleteAppNode(Integer appId) throws KeeperException, InterruptedException {
        String appNode=ZkConfig.APP_NODE+"/"+appId;
        if (zooKeeper.exists(appNode,false) != null) {
            zooKeeper.delete(appNode, -1);
        }
    }

    /**
     * 添加一个过滤器通知
     * @param id
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void addFliters(Integer id) throws KeeperException, InterruptedException {
        String filiters = ZkConfig.APP_FLITER+"/"+id;
        if (zooKeeper.exists(filiters, false) == null) {
            zooKeeper.create(filiters,"".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    /**
     * 删除一个过滤器通知
     * @param id
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void delFliters(Integer id) throws KeeperException, InterruptedException {
        String filiters = ZkConfig.APP_FLITER+"/"+id;
        if (zooKeeper.exists(filiters, false) != null) {
            zooKeeper.delete(filiters,-1);
        }
    }

    /**
     * 添加一个配置信息
     * @param key
     * @param value
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void addConfig(String key,String value) throws KeeperException, InterruptedException {
        String configfile = ZkConfig.APP_CONFIG+"/"+key;
        if (zooKeeper.exists(configfile,false) == null) {
            zooKeeper.create(configfile,value.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            zooKeeper.setData(configfile,value.getBytes(), -1);
        }
    }

    /**
     * 删除一个配置信息
     * @param key
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void deleteConfig(String key) throws KeeperException, InterruptedException {
        String configfile = ZkConfig.APP_CONFIG+"/"+key;
        if (zooKeeper.exists(configfile, false) != null) {
            zooKeeper.delete(configfile,-1);
        }
    }

    /**
     * 添加一个配置信息
     * @param key
     * @param value
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void addConnectPool(String key,String value) throws KeeperException, InterruptedException {
        String configfile = ZkConfig.APP_CONNECT_POOL+"/"+key;
        if (zooKeeper.exists(configfile,false) == null) {
            zooKeeper.create(configfile,value.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            zooKeeper.setData(configfile,value.getBytes(), -1);
        }
    }

    /**
     * 删除一个配置信息
     * @param key
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void deleteConnectPool(String key) throws KeeperException, InterruptedException {
        String configfile = ZkConfig.APP_CONNECT_POOL+"/"+key;
        if (zooKeeper.exists(configfile, false) != null) {
            zooKeeper.delete(configfile,-1);
        }
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }
}
