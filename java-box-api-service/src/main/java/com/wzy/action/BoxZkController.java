package com.wzy.action;

import com.wzy.action.parameter.zk.ServerApi;
import com.wzy.service.ZookeeperService;
import com.wzy.util.annotation.factory.Verification;
import com.wzy.util.jsonvo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoxZkController {

    @Autowired
    ZookeeperService zookeeperService;

    /**
     * 获取注册服务
     * @return
     */
    @RequestMapping(value = "/getServerList")
    public String getServerList(String jsonp) {
        return new JsonVo()
                .setResult(true)
                .setJsonp(jsonp)
                .setBusiness(jsonVo -> {
                    try {
                        jsonVo.setObject(zookeeperService.getServerNodes());
                    } catch (Exception e) {
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 获取某一个服务已经载入的接口
     * @return
     */
    @RequestMapping(value = "/getServerApiList")
    public String getServerApiList(ServerApi serverApi) {
        return Verification.verification(serverApi)
                .setJsonp(serverApi.getJsonp())
                .setBusiness(jsonVo -> {
                    try {
                        jsonVo.setObject(zookeeperService.getBoxApiVoList(serverApi.getRegsionIp()));
                    } catch (Exception e) {
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }
}
