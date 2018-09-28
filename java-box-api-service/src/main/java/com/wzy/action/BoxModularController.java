package com.wzy.action;

import com.wzy.action.parameter.moudular.AddMoudular;
import com.wzy.action.parameter.moudular.GetMoudularLIst;
import com.wzy.action.parameter.moudular.IdMoudular;
import com.wzy.entity.vo.BoxMoudulaVo;
import com.wzy.util.MapUtil;
import com.wzy.util.base.BaseServiceI;
import com.wzy.util.annotation.factory.Verification;
import com.wzy.util.exception.MyExceptionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class BoxModularController {

    @Autowired
    BaseServiceI<BoxMoudulaVo> serviceI;

    /**
     * 查询模块列表
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/getMoudularList")
    public String getMoudularList(GetMoudularLIst parameter){
        return Verification.verification(parameter)
                .setJsonp(parameter.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        Map keys = MapUtil.objectToMap(parameter);
                        jsonVo.setObject(serviceI.getFindList(keys,parameter.getPageNo(),parameter.getPageSize()));
                        jsonVo.setSumPage(serviceI.getFindListCount(keys));
                    }catch (Exception e){
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

     /**
     * 查询模块列表
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/addModular")
    public String addModular(AddMoudular parameter){
        return Verification.verification(parameter)
                .setJsonp(parameter.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        BoxMoudulaVo boxMoudulaVo = new BoxMoudulaVo();
                        BeanUtils.copyProperties(parameter,boxMoudulaVo);
                        serviceI.add(boxMoudulaVo);
                    }catch (Exception e){
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 删除模块
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/delModular")
    public String delModular(IdMoudular parameter){
        return Verification.verification(parameter)
                .setJsonp(parameter.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        serviceI.del(parameter.getId());
                    }catch (Exception e){
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 查询模块列表
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/getOptionMoudularList")
    public String getMoudularOptionList(GetMoudularLIst parameter){
        return Verification.verification(parameter)
                .setJsonp(parameter.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        Map keys = MapUtil.objectToMap(parameter);
                        jsonVo.setObject(serviceI.getFindList(keys,1, Integer.MAX_VALUE));
                    }catch (Exception e){
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }
}
