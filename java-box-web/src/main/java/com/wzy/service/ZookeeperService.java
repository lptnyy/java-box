package com.wzy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzy.entity.vo.BoxApiVo;
import com.wzy.entity.vo.ServerNodeVo;
import com.wzy.util.zookeeper.ZookeeperUtil;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZookeeperService {

    @Autowired
    ZookeeperUtil zookeeperUtil;

    /**
     * 查询所有zookeeper 注册的服务
     * @return
     */
    public List<ServerNodeVo> getServerNodes() throws Exception{
        ZooKeeper zooKeeper = zookeeperUtil.getZooKeeper();
        List<ServerNodeVo> serverNodeVos = new ArrayList<>();
        if (zooKeeper.exists(zookeeperUtil.REGION_SERVER_NODE, false) == null){
            return serverNodeVos;
        }
        List<String> serverNodes =zooKeeper.getChildren(zookeeperUtil.REGION_SERVER_NODE, null);
        serverNodes.forEach(str ->{
            try {
                byte[] jsonStrByte = zooKeeper.getData(zookeeperUtil.REGION_SERVER_NODE+"/"+str, false, null);
                String jsonStrVo = new String(jsonStrByte);
                JSONObject jsonObject = JSON.parseObject(jsonStrVo);
                ServerNodeVo serverNodeVo = JSON.toJavaObject(jsonObject, ServerNodeVo.class);
                serverNodeVos.add(serverNodeVo);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return serverNodeVos;
    }

    /**
     * 查询已经载入的接口
     * @param regsionIp
     * @return
     */
    public List<BoxApiVo> getBoxApiVoList(String regsionIp) throws Exception {
        ZooKeeper zooKeeper = zookeeperUtil.getZooKeeper();
        List<BoxApiVo> boxApiVos = new ArrayList<>();
        String path = zookeeperUtil.REGION_SERVER_NODE;
        if (zooKeeper.exists(path, false) == null){
            return boxApiVos;
        }
        path = zookeeperUtil.REGION_SERVER_NODE+"/"+regsionIp;
        if (zooKeeper.exists(path, false) == null){
            return boxApiVos;
        }
        path = zookeeperUtil.REGION_SERVER_NODE+"/"+regsionIp+zookeeperUtil.NODE_API;
        if (zooKeeper.exists(path, false) == null){
            return boxApiVos;
        }
        List<String> apiLs = zooKeeper.getChildren(path,false);
        String finalPath = path;
        apiLs.forEach(str ->{
            try {
                byte[] jsonStrByte = zooKeeper.getData(finalPath +"/"+str, false, null);
                String jsonStrVo = new String(jsonStrByte);
                JSONObject jsonObject = JSON.parseObject(jsonStrVo);
                BoxApiVo boxApiVo = JSON.toJavaObject(jsonObject, BoxApiVo.class);
                boxApiVos.add(boxApiVo);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return boxApiVos;
    }
}
