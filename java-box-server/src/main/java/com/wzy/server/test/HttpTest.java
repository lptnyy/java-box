package com.wzy.server.test;

import com.wzy.server.config.Config;
import com.wzy.server.jar.api.NetApi;
import com.wzy.server.jar.api.config.BoxApp;

import java.util.List;

public class HttpTest {

    public static void main(String[] args) throws Exception {
        Config.initConfig();
        List<BoxApp> boxAppList = NetApi.getBoxAppList();
        boxAppList.forEach(boxApp -> {
            try {
                NetApi.getBoxAppApiList(boxApp.getAppId().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
