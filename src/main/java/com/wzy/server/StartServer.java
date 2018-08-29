package com.wzy.server;

import com.wzy.server.config.Config;
import com.wzy.server.netty.HttpServer;
import com.wzy.server.netty.HttpServerImpl;

public class StartServer {
    
    public static void main(String[] args) throws Exception {
        Config.initConfig();
        HttpServer httpServer = new HttpServerImpl();
        httpServer.init();
    }
}
