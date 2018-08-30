package com.wzy.service;

import com.wzy.entity.BoxMoudula;
import com.wzy.entity.vo.BoxMoudulaVo;
import com.wzy.mapper.BoxMoudulaMapper;
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
public class ModulaService implements BaseServiceI<BoxMoudulaVo> {

    @Resource
    BoxMoudulaMapper moudulaMapper;

    @Override
    public int add(BoxMoudulaVo boxMoudulaVo) throws Exception {
        BoxMoudula boxMoudula = new BoxMoudula();
        BeanUtils.copyProperties(boxMoudulaVo,boxMoudula);
        return moudulaMapper.add(boxMoudula);
    }

    @Override
    public int del(int id) throws Exception {
        return moudulaMapper.del(id);
    }

    @Override
    public int update(BoxMoudulaVo boxMoudulaVo) throws Exception {
        return 0;
    }

    @Override
    public BoxMoudulaVo get(int id) throws Exception {
        return null;
    }

    @Override
    public List<BoxMoudulaVo> getList(int pageNo, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<BoxMoudulaVo> getFindList(Map<String, Object> keys, int pageNo, int pageSize) throws Exception {
        pageNo = PageUtil.returnPageNo(pageNo,pageSize);
        List<BoxMoudula> dataLs = moudulaMapper.getlist(keys,pageNo,pageSize);
        List<BoxMoudulaVo> returnDatas = new ArrayList<>();
        dataLs.forEach(moudula -> {
            BoxMoudulaVo boxMoudulaVo = new BoxMoudulaVo();
            BeanUtils.copyProperties(moudula, boxMoudulaVo);
            boxMoudulaVo.setCreateTime(DateUtil.getyyMMddHHmmss(moudula.getCreateTime()));
            returnDatas.add(boxMoudulaVo);
        });
        return returnDatas;
    }

    @Override
    public int getFindListCount(Map<String, Object> keys) {
        return moudulaMapper.getListCount(keys);
    }
}
