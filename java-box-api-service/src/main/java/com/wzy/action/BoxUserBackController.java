package com.wzy.action;

import com.wzy.action.parameter.user.GetAppApiList;
import com.wzy.action.parameter.user.GetAppList;
import com.wzy.action.parameter.user.GetFliter;
import com.wzy.action.parameter.user.UpdateAppStats;
import com.wzy.service.ButtApiService;
import com.wzy.service.ZookeeperService;
import com.wzy.util.MapUtil;
import com.wzy.util.PageUtil;
import com.wzy.util.annotation.factory.Verification;
import com.wzy.util.jsonvo.JsonVo;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class BoxUserBackController {

    @Autowired
    ButtApiService buttApiService;

    @Autowired
    ZookeeperService zookeeperService;

    /**
     * 查询上传完毕的应用列表
     * @param getAppList
     * @return
     */
    @RequestMapping(value = "/getAppList")
    public String getAppList(GetAppList getAppList){
        return Verification
                .verification(getAppList)
                .setJsonp(getAppList.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(buttApiService.getList(getAppList.getAppName(), getAppList.getPageNo(), getAppList.getPageSize()));
                        jsonVo.setSumPage(buttApiService.getListCount(getAppList.getAppName()));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                })
                .init()
                .returnJsonString();
    }

    /**
     * 查询上传完毕的应用api列表
     * @param getAppApiList
     * @return
     */
    @RequestMapping(value = "/getAppApiList")
    public String getAppApiList(GetAppApiList getAppApiList){
        return Verification.verification(getAppApiList).setJsonp(getAppApiList.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(buttApiService.getBoxAppApiVoList(getAppApiList.getAppId()));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 删除一个app
     * @param getAppApiList
     * @return
     */
    @RequestMapping(value = "/deleteApp")
    public String deleteApp(GetAppApiList getAppApiList){
        return Verification.verification(getAppApiList).setJsonp(getAppApiList.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(buttApiService.deleteApp(getAppApiList.getAppId()));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 发布/下架一个app
     * @param updateAppStats
     * @return
     */
    @RequestMapping(value = "/updateAppStats")
    public String updateApp(UpdateAppStats updateAppStats){
        return Verification.verification(updateAppStats).setJsonp(updateAppStats.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(buttApiService.updateStats(updateAppStats.getAppId(), updateAppStats.getStats()));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 获取所有注册中心的盒子
     * @return
     */
    @RequestMapping(value = "/getservernodes")
    public String getServerNodes(String jsonp){
        return new JsonVo()
                .setResult(true)
                .setJsonp(jsonp)
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(zookeeperService.getServerNodes());
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 获取所有过滤器
     * @return
     */
    @RequestMapping(value = "/getfliters")
    public String getFiters(GetFliter getFliter){
        return  Verification.verification(getFliter)
                .setJsonp(getFliter.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        getFliter.setPageNo(PageUtil.returnPageNo(getFliter.getPageNo(), getFliter.getPageSize()));
                        Map map = MapUtil.objectToMap(getFliter);
                        jsonVo.setObject(buttApiService.getBoxWorkFilters(map));
                        jsonVo.setSumPage(buttApiService.getBoxWorkFiltersCount(map));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }
}
