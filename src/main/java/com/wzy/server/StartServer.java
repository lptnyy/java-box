package com.wzy.server;

import com.wzy.server.HttpServer;
import com.wzy.server.HttpServerImpl;
import com.wzy.server.config.Config;

public class StartServer {

    public static void main(String[] args) throws Exception {
        Config.initConfig();
        HttpServer httpServer = new HttpServerImpl();
        httpServer.init();
    }
}
