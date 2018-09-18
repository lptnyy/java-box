package com.wzy.service;

import com.wzy.entity.BoxApi;
import com.wzy.entity.BoxMoudula;
import com.wzy.entity.BoxProject;
import com.wzy.entity.vo.BoxApiVo;
import com.wzy.entity.vo.BoxMoudulaVo;
import com.wzy.entity.vo.BoxProjectVo;
import com.wzy.mapper.BoxApiMapper;
import com.wzy.mapper.BoxMoudulaMapper;
import com.wzy.mapper.BoxProjectMapper;
import com.wzy.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ButtApiService{

    @Resource
    BoxApiMapper apiMapper;
    @Resource
    BoxProjectMapper projectMapper;
    @Resource
    BoxMoudulaMapper moudulaMapper;

    /**
     * 根据模块ID获取所有api接口
     * @param moudularId
     * @return
     * @throws Exception
     */
    public List<BoxApiVo> getApiList(Integer moudularId) throws Exception{
        Map map = new HashMap();
        map.put("moudularId", moudularId);
        List<BoxApi> apiList = apiMapper.getList(0,Integer.MAX_VALUE, map);
        List<BoxApiVo> apiVos = new ArrayList<>();
        apiList.forEach(boxApi -> {
            BoxApiVo apiVo = new BoxApiVo();
            BeanUtils.copyProperties(boxApi,apiVo);
            apiVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxApi.getCreateTime()));
            apiVos.add(apiVo);
        });
        return apiVos;
    }

    /**
     * 获取所有项目
     * @return
     * @throws Exception
     */
   public List<BoxProjectVo> getProjectList() throws Exception{
        Map map = new HashMap();
        List<BoxProject> apiList = projectMapper.getList(map,0,Integer.MAX_VALUE);
        List<BoxProjectVo> apiVos = new ArrayList<>();
        apiList.forEach(boxApi -> {
            BoxProjectVo apiVo = new BoxProjectVo();
            BeanUtils.copyProperties(boxApi,apiVo);
            apiVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxApi.getCreateTime()));
            apiVos.add(apiVo);
        });
        return apiVos;
    }

    /**
     * 获取所有模块
     * @param projectId
     * @return
     * @throws Exception
     */
   public List<BoxMoudulaVo> getMoudulaList(Integer projectId) throws Exception{
        Map map = new HashMap();
        map.put("projectId", projectId);
        List<BoxMoudula> apiList = moudulaMapper.getlist(map, 0,Integer.MAX_VALUE);
        List<BoxMoudulaVo> apiVos = new ArrayList<>();
        apiList.forEach(boxApi -> {
            BoxMoudulaVo apiVo = new BoxMoudulaVo();
            BeanUtils.copyProperties(boxApi,apiVo);
            apiVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxApi.getCreateTime()));
            apiVos.add(apiVo);
        });
        return apiVos;
    }
}
