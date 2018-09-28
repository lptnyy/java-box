package com.wzy.action;

import com.wzy.action.parameter.butt.ButtApi;
import com.wzy.action.parameter.butt.ButtModular;
import com.wzy.service.ButtApiService;
import com.wzy.util.annotation.factory.Verification;
import com.wzy.util.exception.MyExceptionUtil;
import com.wzy.util.jsonvo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 服务对接接口
 */
@RestController
public class BoxButtApiController {

    @Autowired
    ButtApiService apiService;

    /**
     * 查询api列表
     *
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/butt/getApiList")
    public String getApiList(ButtApi parameter) {
        return Verification.verification(parameter)
                .setBusiness(jsonVo -> {
                    try {
                        jsonVo.setObject(apiService.getApiList(parameter.getMoudularId()));
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 查询模块列表
     *
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/butt/getMoudularList")
    public String getMoudularList(ButtModular parameter) {
        return Verification.verification(parameter)
                .setBusiness(jsonVo -> {
                    try {
                        jsonVo.setObject(apiService.getMoudulaList(parameter.getProjectId()));
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 查询项目列表
     *
     * @return
     */
    @RequestMapping(value = "/butt/getProjectList")
    public String getApiList() {
        return new JsonVo()
                .setResult(true)
                .setBusiness(jsonVo -> {
                    try {
                        jsonVo.setObject(apiService.getProjectList());
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

}
