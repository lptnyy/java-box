package com.wzy.server.config;

import com.wzy.server.config.vo.BoxConfig;
import com.wzy.server.jar.LoadJar;
import com.wzy.server.log.BoxLogImpl;
import com.wzy.server.log.JavaBoxLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 系统配置
 */
public class Config {

    // 配置日志管理
    public static JavaBoxLog log = new BoxLogImpl();

    // 配置服务
    public static BoxConfig config = new BoxConfig();

    // 配置Jar
    public static LoadJar loadJar = new LoadJar();

    // 初始化配置信息
    public static void initConfig(){
        config.setPort(8000);
        config.setServerMainPath("http://localhost:8762");
        config.setGetApiList("/butt/getApiList");
        config.setGetMouderList("/butt/getMoudularList");
        config.setGetProjectList("/butt/getProjectList");
        config.setJarDownServerUrl("http://localhost:8762/downJar?downUrl=");
        try {
            loadJar.initHttp();
            loadJar.initJar();
        } catch (Exception e) {
            log.error(e);
        }
    }
}
