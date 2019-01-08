package com.wzy.service;

import com.wzy.server.region.ServerNode;
import com.wzy.util.zookeeper.ZookeeperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZookeeperService {

    @Autowired
    ZookeeperUtil zookeeperUtil;

    /**
     * 获取所有注册服务器列表
     * @return
     * @throws Exception
     */
    public List<ServerNode> getServerNodes() throws Exception{
        return zookeeperUtil.serverNodeList();
    }
}
