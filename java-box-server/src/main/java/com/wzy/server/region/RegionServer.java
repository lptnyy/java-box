package com.wzy.server.region;

public interface RegionServer {

    // 绑定服务到注册服务上
    public void regionService(ServerNode serverNode);

    // 服务节点触发
    public void serverNodeTriggering(Object watchedEvent);

    // 项目触发
    public void projectTroggering(Object watchedEvent);

    // 模块触发
    public void moudularTroggering(Object watchedEvent);

    // api节点触发
    public void apiTroggering(Object watchedEvent);

    // 载入jar
    public void jarTroggering(Object watchedEvent);
}
