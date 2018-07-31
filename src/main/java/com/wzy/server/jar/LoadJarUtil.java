package com.wzy.server.jar;

import com.wzy.server.config.Config;
import com.wzy.server.jar.api.NetApi;
import com.wzy.server.jar.api.vo.BoxApiVo;
import com.wzy.server.jar.api.vo.BoxMoudulaVo;
import com.wzy.server.jar.api.vo.BoxProjectVo;
import com.wzy.server.jar.loader.BoxUrlClassLoader;
import com.wzy.server.jar.loader.vo.JarVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadJarUtil {
    static Map<String,BoxProjectVo> addProjectMaps = new HashMap<>();
    static Map<String,BoxMoudulaVo> addMdudulaVoMaps = new HashMap<>();
    static Map<String,BoxApiVo> addApiVoMaps = new HashMap<>();
    static final BoxUrlClassLoader urlClassLoader = new BoxUrlClassLoader();

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
            try {
                urlClassLoader.addJar(jarVo);
            } catch (Exception e) {
               Config.log.error(e);
            }
        });
    }
}
