package com.wzy.action;

import com.wzy.action.parameter.user.GetAppList;
import com.wzy.service.ButtApiService;
import com.wzy.util.annotation.factory.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class BoxUserBackController {

    @Autowired
    ButtApiService buttApiService;

    /**
     * 查询上传完毕的应用列表
     * @param getAppList
     * @return
     */
    @RequestMapping(value = "/getAppList")
    public String getAppList(GetAppList getAppList){
        return Verification.verification(getAppList).setJsonp(getAppList.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(buttApiService.getList(getAppList.getAppName(), getAppList.getPageNo(), getAppList.getPageSize()));
                        jsonVo.setSumPage(buttApiService.getListCount(getAppList.getAppName()));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

}