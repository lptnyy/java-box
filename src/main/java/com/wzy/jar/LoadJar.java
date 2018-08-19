package com.wzy.jar;

import com.wzy.config.Config;
import com.wzy.jar.api.NetApi;
import com.wzy.jar.api.vo.BoxApiVo;
import com.wzy.jar.api.vo.BoxMoudulaVo;
import com.wzy.jar.api.vo.BoxProjectVo;
import com.wzy.jar.loader.BoxUrlClassLoader;
import com.wzy.jar.loader.vo.JarVo;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadJar {
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

    /**
     * 初始化接口jar 执行步骤2
     * @throws Exception
     */
    public void initJar() throws Exception {

        // 初始化加载jar
        addMdudulaVoMaps.forEach((k,v)->{
            JarVo jarVo = new JarVo();
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
        JarVo jarVo = BoxUrlClassLoader.getJar(boxApiVo.getModurRoute());
        Class objClass = jarVo.getClassLoader().loadClass(boxApiVo.getClassFuntion());
        Method method = objClass.getMethod("run", BoxHttpRequest.class, BoxHttpResponse.class);
        Object obj = objClass.newInstance();
        Object result = method.invoke(obj,request,response);
        return (boolean) result;
    }
}