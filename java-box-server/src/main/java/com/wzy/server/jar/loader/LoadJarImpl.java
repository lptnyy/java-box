package com.wzy.server.jar.loader;

import com.wzy.server.config.Config;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.annotation.BoxApi;
import com.wzy.server.jar.api.NetApi;
import com.wzy.server.jar.api.config.BoxApp;
import com.wzy.server.jar.api.config.BoxAppApi;
import com.wzy.server.jar.api.config.BoxFilter;
import com.wzy.server.jar.loader.config.Jar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoadJarImpl implements LoadJar {

    // 存放应用信息
    static Map<Integer, BoxApp> boxAppMap = new HashMap<>();

    // 存放应用关联的api
    static Map<Integer, List<BoxAppApi>> boxAppApiMap = new HashMap<>();

    // 存放api 访问映射路径
    static Map<String,BoxAppApi> httpMap = new HashMap<>();

    // 存放过滤器信息
    static Map<String, BoxFilter> httpFliter = new HashMap<>();

    /**
     * 运行jar当中的方法
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public boolean runClass(BoxHttpRequest request, BoxHttpResponse response) throws Exception {
        BoxAppApi boxAppApi = httpMap.get(request.uri());
        if (boxAppApi == null) return false;
        Jar jar = BoxUrlClassLoader.getJar(boxAppApi.getJarMd5());
        if (jar == null) return false;
        if (jar.getInitObject() == null) {
            synchronized (this) {
                if (jar.getInitObject() == null) {
                    jar.setObjClass(jar.getClassLoader().loadClass(boxAppApi.getRunClass()));
                    Object obj = jar.getObjClass().newInstance();
                    jar.setInitObject(obj);
                    Method method = jar.getObjClass().getMethod(boxAppApi.getRunFunction(), BoxHttpRequest.class, BoxHttpResponse.class);
                    jar.setMethod(method);
                }
            }
        }
        return (boolean) jar.getMethod().invoke(jar.getInitObject(), request,response);
    }


    @Override
    public synchronized void initAppHttp(Integer appId) throws Exception {
        BoxApp boxApp = NetApi.getBoxApp(appId);
        boxAppMap.put(boxApp.getAppId(), boxApp);
        try {
            List<BoxAppApi> boxAppApis = NetApi.getBoxAppApiList(boxApp.getAppId().toString());
            boxAppApiMap.put(boxApp.getAppId(), boxAppApis);
        } catch (Exception e) {
            Config.log.error(e);
        }
    }

    @Override
    public synchronized void initAppHttp(List<String> appIds) {
        if (appIds.size() > boxAppMap.size()) {
            appIds.forEach(str ->{
                if(!boxAppMap.containsKey(Integer.valueOf(str))){
                    BoxApp boxApp = null;
                    try {
                        boxApp = NetApi.getBoxApp(Integer.valueOf(str));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    boxAppMap.put(boxApp.getAppId(), boxApp);
                    try {
                        List<BoxAppApi> boxAppApis = NetApi.getBoxAppApiList(boxApp.getAppId().toString());
                        boxAppApiMap.put(boxApp.getAppId(), boxAppApis);
                        boxAppApis.forEach(v ->{
                            httpMap.put(v.getLinkUrl(), v);
                        });
                    } catch (Exception e) {
                        Config.log.error(e);
                    }
                    Jar jar = new Jar();
                    jar.setBaseId(boxApp.getAppId());
                    jar.setJarDownUrl(boxApp.getJarUrl());
                    jar.setJarMd5(boxApp.getJarMd5());
                    try {
                        BoxUrlClassLoader.addJar(jar);
                    } catch (Exception e) {
                        Config.log.error(e);
                    }
                }
            });
        } else if (appIds.size() == 0){
            boxAppMap.clear();
            boxAppApiMap.clear();
            httpMap.clear();
            BoxUrlClassLoader.removes();
        } else {
            List<BoxApp> appIdlist = new ArrayList<>();
            for(BoxApp boxApp: boxAppMap.values()){
                boolean dis = true;
                for(String str:appIds) {
                    if (Integer.valueOf(str).equals(boxApp.getAppId())) {
                        dis = false;
                    }
                }
                if (dis){
                    appIdlist.add(boxApp);
                }
            }
            for(BoxApp addId:appIdlist){
                BoxUrlClassLoader.remove(addId.getJarMd5(), addId.getAppId());
                List<BoxAppApi> list = boxAppApiMap.get(addId.getAppId());
                list.forEach(boxAppApi -> {
                    httpMap.remove(boxAppApi.getLinkUrl());
                });
                boxAppApiMap.remove(addId.getAppId());
                boxAppMap.remove(addId.getAppId());
            }
        }
    }

    @Override
    public void initFliterHttp(Integer id) {

    }

    @Override
    public void initFliterHttp(List<String> ids) {

    }

    @Override
    public boolean scanJar()
    {
        return false;
    }
}
