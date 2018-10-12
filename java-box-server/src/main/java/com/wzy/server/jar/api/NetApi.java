package com.wzy.server.jar.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzy.server.config.Config;
import com.wzy.server.jar.api.config.BoxApp;
import com.wzy.server.jar.api.config.BoxAppApi;
import com.wzy.util.http.HttpGetUtil;

import java.util.ArrayList;
import java.util.List;

public class NetApi {

    /**
     * 获取应用列表
     * @throws Exception
     */
    public static List<BoxApp> getBoxAppList() throws Exception{
        List<BoxApp> boxApps = new ArrayList<>();
        String returnJson = HttpGetUtil.get(Config.config.getGetApplist(),"");
        JSONObject jsonObject = JSON.parseObject(returnJson);
        boolean result = jsonObject.getBoolean("result");
        if (result) {
            JSONArray jsonArray = jsonObject.getJSONArray("object");
            for(int i = 0; i<jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                BoxApp boxApp = new BoxApp();
                boxApp.setAppId(obj.getInteger("appId"));
                boxApp.setJarMd5(obj.getString("jarMd5"));
                boxApp.setJarUrl(obj.getString("jarUrl"));
                boxApp.setName(obj.getString("name"));
                boxApp.setRoute(obj.getString("route"));
                boxApp.setStats(obj.getInteger("stats"));
                boxApps.add(boxApp);
            }
        } else {
            Config.log.error(jsonObject.getString("msg"));
        }
        return boxApps;
    }

    /**
     * 获取应用列表
     * @throws Exception
     */
    public static BoxApp getBoxApp(Integer appId) throws Exception{
        BoxApp boxApp = null;
        String returnJson = HttpGetUtil.get(Config.config.getGetAppInfo(),"appId="+appId);
        JSONObject jsonObject = JSON.parseObject(returnJson);
        boolean result = jsonObject.getBoolean("result");
        if (result) {
            boxApp = new BoxApp();
            JSONObject obj = jsonObject.getJSONObject("object");
            boxApp.setAppId(obj.getInteger("appId"));
            boxApp.setJarMd5(obj.getString("jarMd5"));
            boxApp.setJarUrl(obj.getString("jarUrl"));
            boxApp.setName(obj.getString("name"));
            boxApp.setRoute(obj.getString("route"));
            boxApp.setStats(obj.getInteger("stats"));
        } else {
            Config.log.error(jsonObject.getString("msg"));
        }
        return boxApp;
    }

    /**
     * 查询应用下的所有api
     * @param appId
     * @return
     * @throws Exception
     */
    public static List<BoxAppApi> getBoxAppApiList(String appId) throws Exception {
       List<BoxAppApi> boxAppApis = new ArrayList<>();
       String returnJson = HttpGetUtil.get(Config.config.getGetAppApiList(),"appId="+appId);
       JSONObject jsonObject = JSON.parseObject(returnJson);
       boolean result = jsonObject.getBoolean("result");
       if (result) {
           JSONArray jsonArray = jsonObject.getJSONArray("object");
           for(int i =0;i<jsonArray.size(); i++) {
               JSONObject obj = jsonArray.getJSONObject(i);
               BoxAppApi boxAppApi = new BoxAppApi();
               boxAppApi.setApiId(obj.getInteger("apiId"));
               boxAppApi.setAppId(obj.getInteger("appId"));
               boxAppApi.setJarMd5(obj.getString("jarMd5"));
               boxAppApi.setLinkUrl(obj.getString("linkUrl"));
               boxAppApi.setName(obj.getString("name"));
               boxAppApi.setRoute(obj.getString("route"));
               boxAppApi.setRunClass(obj.getString("runClass"));
               boxAppApi.setRunFunction(obj.getString("runFunction"));
               boxAppApi.setStats(obj.getInteger("stats"));
               boxAppApis.add(boxAppApi);
           }
       }
       return boxAppApis;
    }
}
