package com.wzy.service;

import com.wzy.entity.BoxProject;
import com.wzy.entity.vo.BoxProjectVo;
import com.wzy.mapper.BoxProjectMapper;
import com.wzy.util.DateUtil;
import com.wzy.util.PageUtil;
import com.wzy.util.base.BaseServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ProjectService implements BaseServiceI<BoxProjectVo>,ProjectServiceI{

    @Resource
    BoxProjectMapper projectMapper;

    @Override
    public int add(BoxProjectVo project) throws Exception{
        BoxProject boxProject = new BoxProject();
        BeanUtils.copyProperties(project,boxProject);
        boxProject.setOpenStat(0);
        return projectMapper.save(boxProject);
    }

    @Override
    public int del(int id) throws Exception{
        return projectMapper.del(id);
    }

    @Override
    public int update(BoxProjectVo boxProjectVo) throws Exception {
        return 0;
    }

    @Override
    public BoxProjectVo get(int id) throws Exception {
        BoxProject project = projectMapper.get(id);
        if (project != null) {
            BoxProjectVo projectVo = new BoxProjectVo();
            BeanUtils.copyProperties(project,projectVo);
            projectVo.setCreateTime(DateUtil.getyyMMddHHmmss(project.getCreateTime()));
            return projectVo;
        }
        return null;
    }

    @Override
    public List<BoxProjectVo> getList(int pageNo, int pageSize) throws Exception {
       return null;
    }

    @Override
    public List<BoxProjectVo> getFindList(Map<String, Object> keys, int pageNo, int pageSize) throws Exception {
        pageNo = PageUtil.returnPageNo(pageNo,pageSize);
        List<BoxProject> datas = projectMapper.getList(keys,pageNo,pageSize);
        List<BoxProjectVo> returnDatas = new ArrayList<>();
        if (datas != null && datas.size() > 0) {
            datas.forEach(boxProject -> {
                BoxProjectVo boxProjectVo = new BoxProjectVo();
                BeanUtils.copyProperties(boxProject,boxProjectVo);
                boxProjectVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxProject.getCreateTime()));
                returnDatas.add(boxProjectVo);
            });
        }
        return returnDatas;
    }

    @Override
    public int getFindListCount(Map<String, Object> keys) {
        return projectMapper.getListCount(keys);
    }

    /**
     * 修改状态
     * @param Id
     * @param stat
     * @return
     */
    public int updateOpenStat(int Id, int stat) throws Exception{
        return projectMapper.updateOpenStat(Id, stat);
    }
}
