package com.wzy.server.region.zookeeper;

import com.alibaba.fastjson.JSON;
import com.wzy.server.config.Config;
import com.wzy.server.jar.loader.LoadJar;
import com.wzy.server.jar.loader.BoxUrlClassLoader;
import com.wzy.server.region.RegionServer;
import com.wzy.server.region.ServerNode;
import com.wzy.server.region.zookeeper.watch.ApiWatch;
import com.wzy.server.region.zookeeper.watch.JarWatch;
import com.wzy.server.region.zookeeper.watch.MoudularWatch;
import com.wzy.server.region.zookeeper.watch.ProjectWatch;
import com.wzy.util.log.JavaBoxLog;
import org.apache.zookeeper.*;

import java.io.IOException;

public class ZkServer implements RegionServer{
    JavaBoxLog log = Config.log;
    LoadJar jars = Config.loadJar;

    // 注册服务目录
    public String REGION_SERVER_NODE="/regionServerNode";
    public String NODE_PROJECT="/project";
    public String NODE_MOUDULAR="/moudlar";
    public String NODE_API = "/api";
    public String NODE_JAR = "/jar";

    @Override
    public void regionService(ServerNode serverNode) {
        //try {
//            ZooKeeper zooKeeper = new ZooKeeper(Config.config.getRegionServerIp() + ":"
//                    + Config.config.getRegsionServerPort(), Config.config.getRegsionServerTimeOut(), new Watcher() {
//                @Override
//                public void process(WatchedEvent watchedEvent) {
//                    if (watchedEvent.getType() != Event.EventType.None)
//                    serverNodeTriggering(watchedEvent);
//                }
//            });
//            if (zooKeeper.exists(REGION_SERVER_NODE, null) == null) {
//                zooKeeper.create(REGION_SERVER_NODE, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            }
//
//            String path = REGION_SERVER_NODE+"/"+serverNode.ip+":"+serverNode.port;
//            log.info("zookeeper创建路径:"+path);
//
//            // 转成Json
//            String beanJson = JSON.toJSONString(serverNode);
//            log.info("zookeeper注册信息："+beanJson);
//
//            // 服务注册
//            if (zooKeeper.exists(path, null) == null) {
//                log.info("zookeeper注册服务信息："+beanJson);
//                zooKeeper.create(path, beanJson.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            } else {
//                zooKeeper.setData(path,beanJson.getBytes(),-1);
//            }
//
//            path = REGION_SERVER_NODE+"/"+serverNode.ip+":"+serverNode.port+NODE_PROJECT;
//            log.info("zookeeper创建路径:"+path);
//
//            // 获取加载的项目信息
//            String projectJson = JSON.toJSONString(jars.getProjectMaps());
//            log.info("zookeeper projectJson json:"+projectJson);
//
//            // 添加项目触发器
//            if (zooKeeper.exists(path, null) == null) {
//                zooKeeper.create(path,projectJson.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//            } else {
//                zooKeeper.setData(path, projectJson.getBytes(), -1);
//            }
//            // 添加一次性触发器
//            zooKeeper.getChildren(path, new ProjectWatch(path, zooKeeper,this));
//
//            // 获取加载的模块信息
//            String moudularJson = JSON.toJSONString(jars.getMdudulaVoMaps());
//            log.info("zookeeper moudularJson json:"+moudularJson);
//
//            // 添加模块触发器
//            path = REGION_SERVER_NODE+"/"+serverNode.ip+":"+serverNode.port+NODE_MOUDULAR;
//            log.info("zookeeper创建路径:"+path);
//            if (zooKeeper.exists(path, null) == null) {
//                zooKeeper.create(path,moudularJson.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//            } else {
//                zooKeeper.setData(path,moudularJson.getBytes(), -1);
//            }
//            zooKeeper.getChildren(path, new MoudularWatch(path, zooKeeper,this));
//
//            // 获取加载的api信息
//            String apiJson = JSON.toJSONString(jars.getApiVoMaps());
//            log.info("zookeeper apiJson json:"+apiJson);
//
//            // 添加APi触发器
//            path = REGION_SERVER_NODE+"/"+serverNode.ip+":"+serverNode.port+NODE_API;
//            log.info("zookeeper创建路径:"+path);
//            if (zooKeeper.exists(path, null) == null) {
//                zooKeeper.create(path,apiJson.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//            } else {
//                zooKeeper.setData(path,apiJson.getBytes(), -1);
//            }
//            zooKeeper.getChildren(path, new ApiWatch(path, zooKeeper, this));
//
//            // 获取载入jar内容
//            String jarJson = JSON.toJSONString(BoxUrlClassLoader.getJarmaps());
//            log.info("zookeeper Jar json:"+jarJson);
//
//            // 添加注入的jar内容
//            path = REGION_SERVER_NODE+"/"+serverNode.ip+":"+serverNode.port+NODE_JAR;
//            log.info("zookeeper创建路径:"+path);
//            if (zooKeeper.exists(path, null) == null) {
//                zooKeeper.create(path,jarJson.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//            } else {
//                zooKeeper.setData(path,jarJson.getBytes(), -1);
//            }
//            zooKeeper.getChildren(path, new JarWatch(path, zooKeeper, this));
//
//        } catch (IOException e) {
//            Config.log.error(e);
//        } catch (InterruptedException e) {
//            Config.log.error(e);
//        } catch (KeeperException e) {
//            Config.log.error(e);
//        }
    }

    @Override
    public void serverNodeTriggering(Object watchedEvent) {
        log.info("节点注册触发");
    }

    @Override
    public void projectTroggering(Object watchedEvent) {
        log.info("项目更新触发");
    }

    @Override
    public void moudularTroggering(Object watchedEvent) {
        log.info("模块更新触发");
    }

    @Override
    public void apiTroggering(Object watchedEvent) {
        log.info("api更新触发");
    }

    @Override
    public void jarTroggering(Object watchedEvent) {
        log.info("jar载入触发");
    }
}
