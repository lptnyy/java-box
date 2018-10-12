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

}
