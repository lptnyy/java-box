package com.wzy.service;

import com.wzy.entity.BoxConfig;
import com.wzy.entity.vo.BoxConfigVo;
import com.wzy.mapper.BoxConfigMapper;
import com.wzy.util.DateUtil;
import com.wzy.util.PageUtil;
import com.wzy.util.zookeeper.ZookeeperUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigService{

    @Resource
    BoxConfigMapper mapper;

    @Autowired
    ZookeeperUtil zookeeperUtil;

    @Transactional
    public int add(BoxConfigVo boxConfigVo) throws Exception {
        BoxConfig boxConfig = new BoxConfig();
        BeanUtils.copyProperties(boxConfigVo,boxConfig);
        int num = mapper.add(boxConfig);
        zookeeperUtil.addConfig(boxConfigVo.getK(), boxConfigVo.getV());
        return num;
    }

    public int del(int id, String key) throws Exception {
        int num = mapper.del(id);
        zookeeperUtil.deleteConfig(key);
        return num;
    }

    public int update(BoxConfigVo boxConfigVo) throws Exception {
        BoxConfig boxConfig = new BoxConfig();
        BeanUtils.copyProperties(boxConfigVo,boxConfig);
        int num = mapper.update(boxConfig);
        zookeeperUtil.addConfig(boxConfig.getK(),boxConfig.getV());
        return num;
    }

    public BoxConfigVo get(int id) throws Exception {
        return null;
    }

    public BoxConfigVo get(String key) throws Exception{
        BoxConfig config = mapper.get(key);
        if (config == null) {
            return null;
        }
        BoxConfigVo boxConfigVo = new BoxConfigVo();
        BeanUtils.copyProperties(config, boxConfigVo);
        boxConfigVo.setCreateTime(DateUtil.getyyMMddHHmmss(config.getCreateTime()));
        return boxConfigVo;
    }

    public List<BoxConfigVo> getList(int pageNo, int pageSize) throws Exception {
        pageNo = PageUtil.returnPageNo(pageNo,pageSize);
        List<BoxConfig> boxConfigs = mapper.getList();
        List<BoxConfigVo> boxConfigVoList = new ArrayList<>();
        boxConfigs.forEach(boxConfig -> {
            BoxConfigVo boxConfigVo = new BoxConfigVo();
            BeanUtils.copyProperties(boxConfig,boxConfigVo);
            boxConfigVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxConfig.getCreateTime()));
            boxConfigVoList.add(boxConfigVo);
        });
        return boxConfigVoList;
    }

    public List<BoxConfigVo> getFindList(Map<String, Object> keys, int pageNo, int pageSize) throws Exception {
        return null;
    }

    public int getFindListCount(Map<String, Object> keys) {
        return mapper.getListCount();
    }

    public int initConfig() throws Exception{
        Map<String,String> mps = new HashMap<>();
        mps.put("service_api_base_url","http://192.168.30.199:9980");
        mps.put("service_api_get_app_list","/butt/getApplist");
        mps.put("service_api_get_app_api_list","/butt/getAppApiList");
        mps.put("service_api_get_id","/butt/getAppId");
        mps.put("service_api_get_fliters","/butt/getfliters");
        mps.put("service_api_get_connect_pool","/butt/getConnectPools");
        mps.put("service_apt_down_jar","/downJar?downUrl=");
        mps.put("server_charset","utf-8");
        for(String key: mps.keySet()) {
            BoxConfigVo boxConfig = get(key);
            if (boxConfig == null) {
                boxConfig = new BoxConfigVo();
                boxConfig.setK(key);
                boxConfig.setV(mps.get(key));
                zookeeperUtil.addConfig(key,mps.get(key));
                add(boxConfig);
            }
        }
        return 0;
    }
}
