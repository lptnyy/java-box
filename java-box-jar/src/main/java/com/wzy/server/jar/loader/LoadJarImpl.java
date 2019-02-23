package com.wzy.server.jar.loader;

import com.wzy.func.fc.*;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.jar.api.NetApi;
import com.wzy.server.jar.api.config.BoxApp;
import com.wzy.server.jar.api.config.BoxAppApi;
import com.wzy.server.jar.api.config.BoxConnectionPool;
import com.wzy.server.jar.api.config.BoxFilter;
import com.wzy.server.jar.loader.config.Jar;
import com.wzy.util.http.UrlPart;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadJarImpl implements ILoadJar {

    static LoadJarImpl loadJar;

    public static ILoadJar getInstance() {
        if (loadJar == null) {
            synchronized (ILoadJar.class) {
                if (loadJar == null) {
                    loadJar = new LoadJarImpl();
                }
            }
        }
        return loadJar;
    }

    ILog log = BoxLog.getInstance();

    // 存放应用信息
    static Map<Integer, BoxApp> boxAppMap = new HashMap<>();

    // 存放应用关联的api
    static Map<Integer, List<BoxAppApi>> boxAppApiMap = new HashMap<>();

    // 存放api 访问映射路径
    static Map<String,BoxAppApi> httpMap = new HashMap<>();

    // 存放过滤器信息
    static Map<String, BoxFilter> httpFliter = new HashMap<>();

    // 没一个拦截器拦截的接口信息
    static Map<String, String> fliterUrl = new HashMap<>();

    /**
     * 运行jar当中的方法
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public boolean runClass(IBoxHttpRequest request, IBoxHttpResponse response) throws Exception {
        BoxAppApi boxAppApi = httpMap.get(request.uri());
        if (boxAppApi == null) return false;
        Jar jar = BoxUrlClassLoader.getJar(boxAppApi.getJarMd5());
        if (jar == null) return false;
        if (jar.getInitObject() == null) {
            synchronized (this) {
                if (jar.getInitObject() == null) {
                    try{
                        jar.setObjClass(jar.getClassLoader().loadClass(boxAppApi.getRunClass()));
                        Object obj = jar.getObjClass().newInstance();
                        BoxUrlClassLoader.setBean(obj);
                        jar.setInitObject(obj);
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }
        Method method = jar.getObjClass().getMethod(boxAppApi.getRunFunction(), IBoxHttpRequest.class, IBoxHttpResponse.class);
        return (boolean) method.invoke(jar.getInitObject(), request,response);
    }

    @Override
    public synchronized void initAppHttp(Integer appId) throws Exception {
        BoxApp boxApp = NetApi.getBoxApp(appId);
        boxAppMap.put(boxApp.getAppId(), boxApp);
        try {
            List<BoxAppApi> boxAppApis = NetApi.getBoxAppApiList(boxApp.getAppId().toString());
            boxAppApiMap.put(boxApp.getAppId(), boxAppApis);
        } catch (Exception e) {
            log.error(e);
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
                        log.error(e);
                    }
                    Jar jar = new Jar();
                    jar.setBaseId(boxApp.getAppId());
                    jar.setJarDownUrl(boxApp.getJarUrl());
                    jar.setJarMd5(boxApp.getJarMd5());
                    try {
                        BoxUrlClassLoader.addJar(jar);
                    } catch (Exception e) {
                        log.error(e);
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

    public BoxFilterRun runFliter(IBoxHttpRequest request, IBoxHttpResponse response)  throws Exception{
        BoxFilterRun filterRun = new BoxFilterRun();
        String pathvalue = fliterUrl.get(request.uri());
        if (pathvalue == null) {
            filterRun.setCode(BoxFilterRun.RUNNULL);
            return filterRun;
        }
        BoxFilter filter = httpFliter.get(pathvalue);
        if (filter == null) {
            filterRun.setCode(BoxFilterRun.RUNNULL);
        }
        Jar jar = BoxUrlClassLoader.getFilterJar(filter.getJarMd5());
        if (jar == null) {
            filterRun.setCode(BoxFilterRun.RUNERROR);
        }
        if (jar.getInitObject() == null) {
            synchronized (this) {
                if (jar.getInitObject() == null) {
                    jar.setObjClass(jar.getClassLoader().loadClass(filter.getClassName()));
                    Object obj = jar.getObjClass().newInstance();
                    BoxUrlClassLoader.setBean(obj);
                    jar.setInitObject(obj);
                }
            }
        }
        WorkFilter boxWorkFilter = (WorkFilter) jar.getInitObject();
        boolean op = boxWorkFilter.service(request,response);
        if (op)
            filterRun.setCode(BoxFilterRun.RUNSU);
        else
            filterRun.setCode(BoxFilterRun.RUNERROR);
        return filterRun;
    }

    @Override
    public synchronized void initFliterHttp(String id) {
        try {
            List<BoxFilter> boxAppApis = NetApi.getBoxFilterList(id);
            boxAppApis.forEach(boxFilter -> {
                Jar jar = new Jar();
                jar.setJarDownUrl(boxFilter.getJarUrl());
                jar.setJarMd5(boxFilter.getJarMd5());
                jar.setBaseId(boxFilter.getId());
                jar.setClassName(boxFilter.getClassName());
                try {
                    BoxUrlClassLoader.addFliterJar(jar);
                    httpFliter.put(boxFilter.getPath(), boxFilter);
                    httpMap.forEach((k,v) ->{
                        if (UrlPart.path(boxFilter.getPath(),k)) {
                            fliterUrl.put(k, boxFilter.getPath());
                        }
                    });
                } catch (Exception e) {
                    log.error(e);
                }
            });
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public synchronized void  removeFliterHttp(String id){
        String deleteKey = "";
        BoxFilter boxFilter = null;
        for (BoxFilter v:httpFliter.values()) {
            if (v.getId().equals(Integer.valueOf(id))){
                deleteKey = v.getPath();
                boxFilter = v;
            }
        }
        httpFliter.remove(deleteKey);
        List<String> keys = new ArrayList<>();
        for(String key: fliterUrl.keySet()) {
            if (fliterUrl.get(key).equals(deleteKey)){
                keys.add(key);
            }
        }
        keys.forEach(key ->{
            fliterUrl.remove(key);
        });
        if (boxFilter != null) {
            try {
                BoxUrlClassLoader.removeFliterJar(boxFilter.getId());
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    @Override
    public synchronized void initFliterHttp(List<String> ids) {

    }

    @Override
    public void initConnectPool(String ids) {
        try {
            List<BoxConnectionPool> boxConnectionPools = NetApi.getBoxConnectPools(ids);
            boxConnectionPools.forEach(boxConnectionPool -> {
                try {
                    Jar jar = new Jar();
                    jar.setBaseId(boxConnectionPool.getId());
                    jar.setJarDownUrl(boxConnectionPool.getJarUrl());
                    jar.setJarMd5(boxConnectionPool.getJarMd5());
                    jar.setClassName(boxConnectionPool.getClassName());
                    BoxUrlClassLoader.addConnectPoolJar(jar);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void removeConnectPool(String id) {
        try {
            BoxUrlClassLoader.removeConnectPoolJar(Integer.valueOf(id));
        } catch (Exception e) {
            log.error(e);
        }
        log.info("ok1"+id);
    }

    @Override
    public boolean scanJar()
    {
        return false;
    }
}
