package com.wzy.server.config;
import com.wzy.server.jar.loader.LoadJarImpl;
import com.wzy.util.log.BoxLog;
import com.wzy.util.log.JavaBoxLog;
/**
 * 系统配置
 */
public class Config {

    // 配置日志管理
    public final static JavaBoxLog log = new BoxLog();

    // 配置服务
    public final static BoxConfig config = new BoxConfig();

    // 配置Jar
    public final static LoadJarImpl loadJar = new LoadJarImpl();

    // 初始化配置信息
    public static void initConfig(){
        config.setServerPort(8000);
        config.setJarDownServerUrl("http://localhost:8762/downJar?downUrl=");
        config.setGetApplist("http://localhost:8762/butt/getApplist");
        config.setGetAppApiList("http://localhost:8762/butt/getAppApiList");
        config.setGetAppInfo("http://localhost:8762/butt/getAppId");
        config.setGetFliter("http://localhost:8762/butt/getfliters");
        config.setRegionServerIp("192.168.30.199");
        config.setRegsionServerPort(2181);
        config.setRegsionServerTimeOut(600000);
        config.setCharSet("gbk");
    }
}
