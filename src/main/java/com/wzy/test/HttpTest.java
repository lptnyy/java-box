package com.wzy.test;

import com.wzy.server.jar.LoadJarUtil;
import com.wzy.server.jar.api.NetApi;
import com.wzy.server.config.Config;

public class HttpTest {

    public static void main(String[] args) throws Exception {
        Config.initConfig();
        LoadJarUtil jarUtil = new LoadJarUtil();
        jarUtil.initHttp();
        jarUtil.initJar();
    }
}
