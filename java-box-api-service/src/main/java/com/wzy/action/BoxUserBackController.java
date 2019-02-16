package com.wzy.action;

import com.wzy.action.parameter.user.*;
import com.wzy.entity.BoxConfig;
import com.wzy.entity.BoxConnectionPool;
import com.wzy.entity.vo.BoxConfigVo;
import com.wzy.server.config.Config;
import com.wzy.service.BoxConnectionPoolService;
import com.wzy.service.ButtApiService;
import com.wzy.service.ConfigService;
import com.wzy.service.ZookeeperService;
import com.wzy.util.MapUtil;
import com.wzy.util.PageUtil;
import com.wzy.util.annotation.factory.Verification;
import com.wzy.util.jsonvo.JsonVo;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Autowired
    ConfigService configService;

    @Autowired
    BoxConnectionPoolService boxConnectionPoolService;

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

    /**
     * 刪除過濾器
     * @return
     */
    @RequestMapping(value = "/deletefliters")
    public String deleteFiters(Integer id, String jsonp){
        return  new JsonVo().setResult(true)
                .setJsonp(jsonp)
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(buttApiService.deleteButBoxWorkFilters(id));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 發佈 取消
     * @return
     */
    @RequestMapping(value = "/updateflitersStat")
    public String updateflitersStat(Integer id,Integer stat, String jsonp){
        return  new JsonVo().setResult(true)
                .setJsonp(jsonp)
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(buttApiService.updateBoxWorkFileters(id,stat));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 查看所有配置文件
     * @return
     */
    @RequestMapping(value = "/getConfigs")
    public String getConfigs(GetAddConfig addConfig){
        return  Verification.verification(addConfig)
                .setJsonp(addConfig.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(configService.getList(addConfig.getPageNo(), Integer.MAX_VALUE));
                    } catch (Exception e) {
                       jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 保存配置信息
     * @return
     */
    @RequestMapping(value = "/addConfig")
    public String addConfig(AddConfig addConfig){
        return  Verification.verification(addConfig)
                .setJsonp(addConfig.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        BoxConfigVo boxConfigVo = configService.get(addConfig.getKey());
                        if (boxConfigVo != null) {
                            jsonVo.setResult(false);
                            jsonVo.setMsg("key不能重複");
                            return jsonVo;
                        }
                        boxConfigVo = new BoxConfigVo();
                        boxConfigVo.setK(addConfig.getKey());
                        boxConfigVo.setV(addConfig.getValue());
                        jsonVo.setObject(configService.add(boxConfigVo));
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 初始化配置信息
     * @return
     */
    @RequestMapping(value = "/initConfig")
    public String initConfig(String jsonp){
        return  new JsonVo().setResult(true)
                .setJsonp(jsonp)
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(configService.initConfig());
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 刪除一個key
     * @return
     */
    @RequestMapping(value = "/delConfig")
    public String delConfig(delConfig delConfig){
        return  Verification.verification(delConfig)
                .setJsonp(delConfig.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(configService.del(delConfig.getId(), delConfig.getKey()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 修改一個Key
     * @return
     */
    @RequestMapping(value = "/updateConfig")
    public String updateConfig(AddConfig addConfig){
        return  Verification.verification(addConfig)
                .setJsonp(addConfig.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        BoxConfigVo boxConfigVo = new BoxConfigVo();
                        boxConfigVo.setK(addConfig.getKey());
                        boxConfigVo.setV(addConfig.getValue());
                        configService.update(boxConfigVo);
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 获取连接池列表
     * @param jsonp
     * @return
     */
    @RequestMapping(value = "/getconnectpool")
    public String getConnectionPool(String jsonp){
        return  new JsonVo().setResult(true)
                .setJsonp(jsonp)
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(boxConnectionPoolService.getList(0,0));
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 获取连接池列表
     * @param delConnectionPool
     * @return
     */
    @RequestMapping(value = "/deleteconnectpool")
    public String deleteConnectionPool(delConnectionPool delConnectionPool){
        return  Verification.verification(delConnectionPool)
                .setJsonp(delConnectionPool.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        boxConnectionPoolService.del(delConnectionPool.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 获取连接池列表
     * @param delConnectionPool
     * @return
     */
    @RequestMapping(value = "/statconnectpool")
    public String statConnectionPool(StatConnectionPool delConnectionPool){
        return  Verification.verification(delConnectionPool)
                .setJsonp(delConnectionPool.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        BoxConnectionPool boxConnectionPool = new BoxConnectionPool();
                        boxConnectionPool.setId(delConnectionPool.getId());
                        boxConnectionPool.setStat(delConnectionPool.getStat());
                        boxConnectionPoolService.updateStats(boxConnectionPool);
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }
}
