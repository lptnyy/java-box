package com.wzy.server.master;

public interface RegionServer {

    // 绑定服务到注册服务上
    public void regionService(ServerNode serverNode);
}
