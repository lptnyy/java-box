package com.wzy.service;
import com.wzy.entity.BoxApi;
import com.wzy.entity.vo.BoxApiVo;
import com.wzy.mapper.BoxApiMapper;
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
public class ApiService implements BaseServiceI<BoxApiVo>{

    @Resource
    BoxApiMapper apiMapper;

    @Override
    public int add(BoxApiVo boxApiVo) throws Exception {
        BoxApi boxApi = new BoxApi();
        BeanUtils.copyProperties(boxApiVo,boxApi);
        return apiMapper.add(boxApi);
    }

    @Override
    public int del(int id) throws Exception {
        return apiMapper.del(id);
    }

    @Override
    public int update(BoxApiVo boxApiVo) throws Exception {
        return 0;
    }

    @Override
    public BoxApiVo get(int id) throws Exception {
        return null;
    }

    @Override
    public List<BoxApiVo> getList(int pageNo, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<BoxApiVo> getFindList(Map<String, Object> keys, int pageNo, int pageSize) throws Exception {
        pageNo = PageUtil.returnPageNo(pageNo,pageSize);
        List<BoxApi> datas = apiMapper.getList(pageNo,pageSize,keys);
        List<BoxApiVo> returnDatas = new ArrayList<>();
        if (datas != null && datas.size() > 0) {
            datas.forEach(boxProject -> {
                BoxApiVo boxProjectVo = new BoxApiVo();
                BeanUtils.copyProperties(boxProject,boxProjectVo);
                boxProjectVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxProject.getCreateTime()));
                returnDatas.add(boxProjectVo);
            });
        }
        return returnDatas;
    }

    @Override
    public int getFindListCount(Map<String, Object> keys) {
        return apiMapper.getListCount(keys);
    }
}
