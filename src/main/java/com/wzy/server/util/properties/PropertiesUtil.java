package com.wzy.server.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    /**
     * 获取配置文件内容
     * @param key
     * @return
     */
    public static String getConfigKey(String key){
       String configUrl = System.getProperty("user.dir");
       try(InputStream is = new FileInputStream(new File(configUrl))){
           Properties p = new Properties();
           p.load(is);
           return p.getProperty(key);
       }catch (Exception e){

       }
       return null;
    }
}
