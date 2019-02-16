package com.wzy.server.config;
import com.wzy.func.fc.IConfig;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.util.properties.PropertiesUtil;
import org.apache.zookeeper.KeeperException;

/**
 * 系统配置
 */
public class Config {
    static ILog log = BoxLog.getInstance();

    // 配置服务
    public final static BoxConfig config = new BoxConfig();

    // 初始化配置信息
    public static boolean initConfig(){
        if (initEnvConfig())
            return true;
        else if(initProConfig())
            return true;
        return false;
    }

    // 读取环境变量
    public static boolean initEnvConfig(){
        try {
            String zookeeper_ip= System.getenv("zookeeper_ip");
            String zookeeper_port= System.getenv("zookeeper_port");
            String zookeeper_time_out= System.getenv("zookeeper_time_out");
            String server_port= System.getenv("server_port");
            String server_ip= System.getenv("server_ip");
            config.setServerPort(Integer.valueOf(server_port));
            config.setRegionServerIp(zookeeper_ip);
            config.setRegsionServerPort(Integer.valueOf(zookeeper_port));
            config.setRegsionServerTimeOut(Integer.valueOf(zookeeper_time_out));
            config.setServerIp(server_ip);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // 读取环境变量
    public static boolean initProConfig(){
        try {
            String zookeeper_ip= PropertiesUtil.getConfigKey("zookeeper_ip");
            String zookeeper_port= PropertiesUtil.getConfigKey("zookeeper_port");
            String zookeeper_time_out= PropertiesUtil.getConfigKey("zookeeper_time_out");
            String server_port= PropertiesUtil.getConfigKey("server_port");
            String server_ip= PropertiesUtil.getConfigKey("server_ip");
            config.setServerPort(Integer.valueOf(server_port));
            config.setRegionServerIp(zookeeper_ip);
            config.setRegsionServerPort(Integer.valueOf(zookeeper_port));
            config.setRegsionServerTimeOut(Integer.valueOf(zookeeper_time_out));
            config.setServerIp(server_ip);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean initZkConfig(IConfig zkconfig) throws KeeperException, InterruptedException {
        try{
            String service_api_base_url= zkconfig.getValue("service_api_base_url");
            String service_api_get_app_list= zkconfig.getValue("service_api_get_app_list");
            String service_api_get_app_api_list= zkconfig.getValue("service_api_get_app_api_list");
            String service_api_get_id= zkconfig.getValue("service_api_get_id");
            String service_api_get_fliters= zkconfig.getValue("service_api_get_fliters");
            String service_apt_down_jar= zkconfig.getValue("service_apt_down_jar");
            String server_charset= zkconfig.getValue("server_charset");
            String service_api_get_connect_pool = zkconfig.getValue("service_api_get_connect_pool");
            config.setJarDownServerUrl(service_api_base_url + service_apt_down_jar);
            config.setGetApplist(service_api_base_url + service_api_get_app_list);
            config.setGetAppApiList(service_api_base_url + service_api_get_app_api_list);
            config.setGetAppInfo(service_api_base_url + service_api_get_id);
            config.setGetFliter(service_api_base_url + service_api_get_fliters);
            config.setCharSet(server_charset);
            config.setGetConnectPools(service_api_base_url+service_api_get_connect_pool);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
