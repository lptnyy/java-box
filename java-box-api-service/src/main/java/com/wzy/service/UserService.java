package com.wzy.service;

import com.wzy.entity.BoxUser;
import com.wzy.entity.vo.BoxUserVo;
import com.wzy.mapper.BoxUserMapper;
import com.wzy.util.DateUtil;
import com.wzy.util.RandomUtil;
import com.wzy.util.base.BaseServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements BaseServiceI<BoxUserVo>, UserServiceI {

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
       return null;
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


    @Override
    @Transactional
    public BoxUserVo checkUser(String userName, String passWord) {
        Map<String, String> keys = new HashMap<>();
        keys.put("user_name", userName);
        keys.put("user_pass", passWord);
        BoxUserVo boxUserVo = new BoxUserVo();
        BoxUser boxUser = boxUserMapper.get(keys);
        if (boxUser == null) return null;
        String code = RandomUtil.getOrderNum();
        boxUserMapper.updateToKen(boxUser.getUser_id(), code);
        BeanUtils.copyProperties(boxUser, boxUserVo);
        boxUserVo.setUser_token(code);
        boxUserVo.setLogin_time(DateUtil.getyyMMddHHmmss(boxUser.getLogin_time()));
        boxUserVo.setCreate_time(DateUtil.getyyMMddHHmmss(boxUser.getCreate_time()));
        return boxUserVo;
    }

    @Override
    public boolean checkUserToken(String userId, String token) {
        Map<String, String> keys = new HashMap<>();
        keys.put("user_id", userId);
        keys.put("user_token", token);
        BoxUser boxUser = boxUserMapper.get(keys);
        if (boxUser == null) return false;
        return true;
    }
}
