package com.wzy.service;

import com.wzy.entity.BoxConnectionPool;
import com.wzy.entity.vo.BoxConnectionPoolVo;
import com.wzy.mapper.BoxConnectionMapper;
import com.wzy.util.base.BaseServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BoxConnectionService implements BaseServiceI<BoxConnectionPoolVo> {
    @Resource
    BoxConnectionMapper mapper;

    @Override
    public int add(BoxConnectionPoolVo boxConnectionPoolVo) throws Exception {
        BoxConnectionPool connectionPool = new BoxConnectionPool();
        BeanUtils.copyProperties(boxConnectionPoolVo, connectionPool);
        return mapper.save(connectionPool);
    }

    @Override
    public int del(int id) throws Exception {
        return 0;
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
        return null;
    }

    @Override
    public List<BoxConnectionPoolVo> getFindList(Map<String, Object> keys, int pageNo, int pageSize) throws Exception {
        return null;
    }

    @Override
    public int getFindListCount(Map<String, Object> keys) {
        return 0;
    }
}
