package com.wzy.service;

public interface ProjectServiceI {

    /**
     * 修改状态
     * @param Id
     * @param stat
     * @return
     */
    public int updateOpenStat(int Id, int stat) throws Exception;

}
