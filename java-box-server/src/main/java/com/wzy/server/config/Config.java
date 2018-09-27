package com.wzy.server.config;

import com.wzy.server.jar.LoadJarImpl;
import com.wzy.util.log.BoxLog;
import com.wzy.util.log.JavaBoxLog;
/**
 * 系统配置
 */
public class Config {

    // 配置日志管理
    public static JavaBoxLog log = new BoxLog();

    // 配置服务
    public static BoxConfig config = new BoxConfig();

    // 配置Jar
    public static LoadJarImpl loadJar = new LoadJarImpl();

    // 初始化配置信息
    public static void initConfig(){
        config.setAdminPort(8001);
        config.setOpenAdmin(true);
        config.setServerPort(8000);
        config.setServerMainPath("http://localhost:8762");
        config.setGetApiList("/butt/getApiList");
        config.setGetMouderList("/butt/getMoudularList");
        config.setGetProjectList("/butt/getProjectList");
        config.setJarDownServerUrl("http://localhost:8762/downJar?downUrl=");
        config.setRegionServerIp("localhost");
        config.setRegsionServerPort(2181);
        config.setRegsionServerTimeOut(3000);
        try {
            loadJar.initHttp();
            loadJar.initJar();
        } catch (Exception e) {
            log.error(e);
        }
    }
}
