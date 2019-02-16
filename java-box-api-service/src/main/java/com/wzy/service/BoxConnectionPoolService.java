package com.wzy.service;

import com.wzy.entity.BoxConnectionPool;
import com.wzy.entity.vo.BoxConnectionPoolVo;
import com.wzy.mapper.BoxConnectionPoolMapper;
import com.wzy.util.DateUtil;
import com.wzy.util.base.BaseServiceI;
import com.wzy.util.zookeeper.ZookeeperUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BoxConnectionPoolService implements BaseServiceI<BoxConnectionPoolVo> {
    @Resource
    BoxConnectionPoolMapper mapper;
    @Autowired
    ZookeeperUtil zookeeperUtil;

    @Override
    public int add(BoxConnectionPoolVo boxConnectionPoolVo) throws Exception {
        BoxConnectionPool connectionPool = new BoxConnectionPool();
        BeanUtils.copyProperties(boxConnectionPoolVo, connectionPool);
        return mapper.save(connectionPool);
    }

    @Override
    public int del(int id) throws Exception {
        return mapper.delete(id);
    }

    @Override
    public int update(BoxConnectionPoolVo boxConnectionPoolVo) throws Exception {
        return 0;
    }

    @Override
    public BoxConnectionPoolVo get(int id) throws Exception {
        return null;
    }

    @Override
    public List<BoxConnectionPoolVo> getList(int pageNo, int pageSize) throws Exception {
        List<BoxConnectionPool> boxConnectionPools = mapper.getList();
        List<BoxConnectionPoolVo> boxConnectionPoolVos = new ArrayList<>();
        boxConnectionPools.forEach(boxConnectionPool -> {
            BoxConnectionPoolVo boxConnectionPoolVo = new BoxConnectionPoolVo();
            BeanUtils.copyProperties(boxConnectionPool, boxConnectionPoolVo);
            boxConnectionPoolVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxConnectionPool.getCreateTime()));
            boxConnectionPoolVos.add(boxConnectionPoolVo);
        });
        return boxConnectionPoolVos;
    }

    @Override
    public List<BoxConnectionPoolVo> getFindList(Map<String, Object> keys, int pageNo, int pageSize) throws Exception {
        List<BoxConnectionPool> boxConnectionPools = mapper.getInIdList(keys.get("id").toString());
        List<BoxConnectionPoolVo> boxConnectionPoolVos = new ArrayList<>();
        boxConnectionPools.forEach(boxConnectionPool -> {
            BoxConnectionPoolVo boxConnectionPoolVo = new BoxConnectionPoolVo();
            BeanUtils.copyProperties(boxConnectionPool, boxConnectionPoolVo);
            boxConnectionPoolVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxConnectionPool.getCreateTime()));
            boxConnectionPoolVos.add(boxConnectionPoolVo);
        });
        return boxConnectionPoolVos;
    }

    @Override
    public int getFindListCount(Map<String, Object> keys) {
        return 0;
    }

    public int updateStats(BoxConnectionPool pool) throws Exception{
        if (pool.getStat().equals(1)){
            zookeeperUtil.addConnectPool(pool.getId().toString(),pool.getId().toString());
        } else {
            zookeeperUtil.deleteConnectPool(pool.getId().toString());
        }
        return mapper.updateStat(pool.getId(),pool.getStat());
    }
}
