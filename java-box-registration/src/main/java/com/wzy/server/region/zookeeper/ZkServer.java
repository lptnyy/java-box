package com.wzy.server.region.zookeeper;

import com.alibaba.fastjson.JSON;
import com.wzy.func.fc.ILoadJar;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.config.Config;
import com.wzy.server.jar.loader.LoadJarImpl;
import com.wzy.server.region.RegionServer;
import com.wzy.server.region.ServerNode;
import com.wzy.server.region.zookeeper.watch.AppWatch;
import com.wzy.server.region.zookeeper.watch.ConfigWatch;
import com.wzy.server.region.zookeeper.watch.ConnectPoolWatch;
import com.wzy.server.region.zookeeper.watch.FliterWatch;
import com.wzy.util.zookeeper.ZkConfig;
import org.apache.zookeeper.*;
import java.util.List;

public class ZkServer implements RegionServer {
    ILog log = BoxLog.getInstance();
    static ZooKeeper zooKeeper = null;
    ILoadJar loadJar = LoadJarImpl.getInstance();
    boolean isConnect = true;

    public ZooKeeper getZooKeeper(){
        return zooKeeper;
    }

    @Override
    public void regionService() {
        try {

            // 创建连接客户端
            zooKeeper = new ZooKeeper(Config.config.getRegionServerIp() + ":"
                    + Config.config.getRegsionServerPort(), Config.config.getRegsionServerTimeOut(), new Watcher() {

                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        log.info("zookeeper 初始化成功");
                        try {
                            isConnect = false;
                            log.info("初始化节点成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * 初始化节点数据
     * @param serverNode
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void initNode(ServerNode serverNode) throws KeeperException, InterruptedException {

        // 创建服务根节点
        if (zooKeeper.exists(ZkConfig.REGION_SERVER,false) == null) {
            zooKeeper.create(ZkConfig.REGION_SERVER, "".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }

        // 创建服务节点
        String nodePath = ZkConfig.REGION_SERVER+"/"+serverNode.ip+":"+serverNode.port;
        if (zooKeeper.exists(nodePath,false) == null) {
            zooKeeper.create(nodePath,JSON.toJSONBytes(serverNode), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        }

        // 监控APP节点
        String appNode = ZkConfig.APP_NODE;
        if (zooKeeper.exists(appNode,false) == null)
            zooKeeper.create(appNode,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.getChildren(appNode, new AppWatch(appNode, zooKeeper));

        // 监控过滤器节点
        String fliterNode = ZkConfig.APP_FLITER;
        if (zooKeeper.exists(fliterNode,false) == null)
            zooKeeper.create(fliterNode,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.getChildren(fliterNode, new FliterWatch(fliterNode, zooKeeper));

        // 监控过滤器节点
        String configNode = ZkConfig.APP_CONFIG;
        if (zooKeeper.exists(configNode,false) == null)
            zooKeeper.create(configNode,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.getChildren(configNode, new ConfigWatch(configNode, zooKeeper));

        String connectPoolsNode = ZkConfig.APP_CONNECT_POOL;
        if (zooKeeper.exists(connectPoolsNode,false) == null)
            zooKeeper.create(connectPoolsNode,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.getChildren(connectPoolsNode, new ConnectPoolWatch(connectPoolsNode, zooKeeper));

        initAppNode(zooKeeper);
        initFilterNode(zooKeeper);
        initConnectPool(zooKeeper);
    }

    /**
     * 初始化jar信息
     * @param zooKeeper
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void initAppNode(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        if (zooKeeper.exists(ZkConfig.APP_NODE,false) != null){
            List<String> stringList = zooKeeper.getChildren(ZkConfig.APP_NODE, false);
            if (stringList.size()>0) {
                loadJar.initAppHttp(stringList);
            }
        }
    }

    /**
     * 初始化过滤器
     * @param zooKeeper
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void initFilterNode(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        if (zooKeeper.exists(ZkConfig.APP_FLITER,false) != null){
            List<String> stringList = zooKeeper.getChildren(ZkConfig.APP_FLITER, false);
            stringList.forEach(str->{
                FliterWatch.initWater(str,zooKeeper);
            });
        }
    }

    public void initConnectPool(ZooKeeper zooKeeper) throws KeeperException, InterruptedException{
        if (zooKeeper.exists(ZkConfig.APP_CONNECT_POOL,false) != null){
            List<String> stringList = zooKeeper.getChildren(ZkConfig.APP_CONNECT_POOL, false);
            stringList.forEach(str->{
                ConnectPoolWatch.initWater(str,zooKeeper);
            });
        }
    }

//    @Override
//    public List<String> keys() {
//        return null;
//    }
//
//    @Override
//    public List<String> values() {
//        return null;
//    }
//
//    @Override
//    public String getValue(String key) throws KeeperException, InterruptedException {
//        String configNode = ZkConfig.APP_CONFIG+"/"+key;
//        if (zooKeeper.exists(configNode,false) != null) {
//            return new String(zooKeeper.getData(configNode,false,null));
//        }
//        return "";
//    }
//
//    @Override
//    public IConfig addView(String key, String value) {
//        return null;
//    }
//
    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }
}
