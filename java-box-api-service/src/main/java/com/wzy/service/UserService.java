package com.wzy.service;

import com.wzy.entity.BoxUser;
import com.wzy.entity.vo.BoxUserVo;
import com.wzy.mapper.BoxUserMapper;
import com.wzy.util.DateUtil;
import com.wzy.util.base.BaseServiceI;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class UserService implements BaseServiceI<BoxUserVo> {

    @Resource
    BoxUserMapper boxUserMapper;

    @Override
    public int add(BoxUserVo boxUserVo) throws Exception {
        BoxUser user = new BoxUser();
        BeanUtils.copyProperties(boxUserVo, user);
        return boxUserMapper.add(user);
    }

    @Override
    public int del(int id) throws Exception {
        return boxUserMapper.del(id);
    }

    @Override
    public int update(BoxUserVo boxUserVo) throws Exception {
        BoxUser user = new BoxUser();
        BeanUtils.copyProperties(boxUserVo, user);
        return 0;
    }

    @Override
    public BoxUserVo get(int id) throws Exception {
        BoxUserVo boxUserVo = new BoxUserVo();
        BoxUser boxUser = boxUserMapper.get(id);
        BeanUtils.copyProperties(boxUser, boxUserVo);
        boxUserVo.setCreate_time(DateUtil.getyyMMddHHmmss(boxUser.getCreate_time()));
        boxUserVo.setLogin_time(DateUtil.getyyMMddHHmmss(boxUser.getLogin_time()));
        return boxUserVo;
    }

    @Override
    public List<BoxUserVo> getList(int pageNo, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<BoxUserVo> getFindList(Map<String, Object> keys, int pageNo, int pageSize) throws Exception {
        return null;
    }

    @Override
    public int getFindListCount(Map<String, Object> keys) {
        return 0;
    }
}
