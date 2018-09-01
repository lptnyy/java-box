package com.wzy.action;

import com.wzy.action.parameter.api.AddApi;
import com.wzy.action.parameter.api.GetApiLIst;
import com.wzy.action.parameter.api.IdApi;
import com.wzy.action.parameter.moudular.AddMoudular;
import com.wzy.action.parameter.moudular.GetMoudularLIst;
import com.wzy.action.parameter.moudular.IdMoudular;
import com.wzy.entity.vo.BoxApiVo;
import com.wzy.entity.vo.BoxMoudulaVo;
import com.wzy.util.MapUtil;
import com.wzy.util.annotation.factory.Verification;
import com.wzy.util.base.BaseServiceI;
import com.wzy.util.exception.MyExceptionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BoxApiController {

    @Autowired
    BaseServiceI<BoxApiVo> serviceI;

    /**
     * 查询api列表
     *
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/getApiList")
    public String getApiList(GetApiLIst parameter) {
        return Verification.verification(parameter)
                .setJsonp(parameter.getJsonp())
                .setBusiness(jsonVo -> {
                    try {
                        Map map = MapUtil.objectToMap(parameter);
                        jsonVo.setObject(serviceI.getFindList(map, parameter.getPageNo(), parameter.getPageSize()));
                        jsonVo.setSumPage(serviceI.getFindListCount(map));
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 添加api
     *
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/addApi")
    public String addApi(AddApi parameter) {
        return Verification.verification(parameter)
                .setJsonp(parameter.getJsonp())
                .setBusiness(jsonVo -> {
                    try {
                        BoxApiVo boxApiVo = new BoxApiVo();
                        BeanUtils.copyProperties(parameter,boxApiVo);
                        jsonVo.setObject(serviceI.add(boxApiVo));
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 删除api
     *
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/delApi")
    public String delApi(IdApi parameter) {
        return Verification.verification(parameter)
                .setJsonp(parameter.getJsonp())
                .setBusiness(jsonVo -> {
                    try {
                        serviceI.del(parameter.getId());
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }
}
