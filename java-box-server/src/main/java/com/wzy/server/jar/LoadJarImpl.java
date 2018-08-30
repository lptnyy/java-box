package com.wzy.server.jar;

import com.wzy.server.config.Config;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.api.NetApi;
import com.wzy.server.jar.api.config.BoxApiVo;
import com.wzy.server.jar.api.config.BoxMoudulaVo;
import com.wzy.server.jar.api.config.BoxProjectVo;
import com.wzy.server.jar.loader.BoxUrlClassLoader;
import com.wzy.server.jar.loader.config.Jar;
import com.wzy.server.work.BoxWork;
import com.wzy.server.work.proxy.BoxWorkProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadJarImpl implements LoadJar{
    static Map<String,BoxProjectVo> addProjectMaps = new HashMap<>();
    static Map<String,BoxMoudulaVo> addMdudulaVoMaps = new HashMap<>();
    static Map<String,BoxApiVo> addApiVoMaps = new HashMap<>();

    /**
     * 初始化Http 执行步骤1
     */
    public void initHttp() throws Exception {
        List<BoxProjectVo> projectVos = NetApi.getProjectList();
        projectVos.forEach(boxProjectVo -> {
            addProjectMaps.put(boxProjectVo.getRoute(), boxProjectVo);
            try {
                List<BoxMoudulaVo> boxMoudulaVos = NetApi.getNoudulaVo(boxProjectVo.getProjectId().toString());
                boxMoudulaVos.forEach(boxMoudulaVo -> {
                    addMdudulaVoMaps.put(boxProjectVo.getRoute()+boxMoudulaVo.getModdularRoute(), boxMoudulaVo);
                    try {
                        List<BoxApiVo> boxApiVos = NetApi.getApiVo(boxMoudulaVo.getMoudularId().toString());
                        boxApiVos.forEach(boxApiVo -> {
                            boxApiVo.setModurRoute(boxProjectVo.getRoute()+boxMoudulaVo.getModdularRoute());
                            addApiVoMaps.put(boxProjectVo.getRoute()+boxMoudulaVo.getModdularRoute()+boxApiVo.getApiRoute(), boxApiVo);
                        });
                    } catch (Exception e) {
                        Config.log.error(e);
                    }
                });
            } catch (Exception e) {
                Config.log.error(e);
            }
        });
    }

    @Override
    public List<BoxProjectVo> getProjectMaps() {
        List<BoxProjectVo> projectVos = new ArrayList<>();
        addProjectMaps.forEach((k,v) -> {
            projectVos.add(v);
        });
        return projectVos;
    }

    @Override
    public List<BoxMoudulaVo> getMdudulaVoMaps() {
        List<BoxMoudulaVo> moudulaVos = new ArrayList<>();
        addMdudulaVoMaps.forEach((k,v) -> {
            moudulaVos.add(v);
        });
        return moudulaVos;
    }

    @Override
    public List<BoxApiVo> getApiVoMaps() {
        List<BoxApiVo> apiVos = new ArrayList<>();
        addApiVoMaps.forEach((k,v) ->{
            apiVos.add(v);
        });
        return apiVos;
    }


    /**
     * 初始化接口jar 执行步骤2
     * @throws Exception
     */
    public void initJar() throws Exception {

        // 初始化加载jar
        addMdudulaVoMaps.forEach((k,v)->{
            Jar jarVo = new Jar();
            jarVo.setHttpUrl(k);
            jarVo.setJarDownUrl(Config.config.getJarDownServerUrl()+v.getJarUrl());
            jarVo.setJarVersion(v.getJarVersion());
            jarVo.setJarMd5(v.getJarMd5());
            try {
                BoxUrlClassLoader.addJar(jarVo);
            } catch (Exception e) {
               Config.log.error(e);
            }
        });
    }
    
    public boolean runClass(BoxHttpRequest request, BoxHttpResponse response) throws Exception{
        String uri = "";
        if(request.uri().indexOf("?") != -1) {
            uri = request.uri().substring(0,request.uri().indexOf("?"));
        } else {
            uri = request.uri();
        }
        BoxApiVo boxApiVo = addApiVoMaps.get(uri);
        Jar jarVo = BoxUrlClassLoader.getJar(boxApiVo.getModurRoute());
        Class objClass = jarVo.getClassLoader().loadClass(boxApiVo.getClassFuntion());
        Object obj = objClass.newInstance();
        BoxWorkProxy workProxy = new BoxWorkProxy((BoxWork) obj);
        return workProxy.invoke(request,response);
    }
}
