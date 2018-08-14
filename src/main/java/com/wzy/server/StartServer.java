package com.wzy.server;

import com.wzy.config.Config;

public class StartServer {

    public static void main(String[] args) throws Exception {
        Config.initConfig();
        HttpServer httpServer = new HttpServerImpl();
        httpServer.init();
    }
}
