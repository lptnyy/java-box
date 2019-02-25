package com.wzy.box.server.registration;

import com.wzy.server.region.RegionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class BoxApplicationRunner implements ApplicationRunner {

    @Autowired
    RegionServer regionServer;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        // 注册服务
        regionServer.regionService();
    }
}
