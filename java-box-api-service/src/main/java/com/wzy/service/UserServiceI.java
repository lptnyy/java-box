package com.wzy.service;

import com.wzy.entity.vo.BoxUserVo;

public interface UserServiceI {

    /**
     * 验证用户是否存在
     * @param userName
     * @param passWord
     * @return
     */
    public BoxUserVo checkUser(String userName, String passWord);
}
