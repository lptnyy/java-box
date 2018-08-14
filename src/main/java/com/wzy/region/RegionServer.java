package com.wzy.region;

import com.wzy.region.vo.ServerNode;

public interface RegionServer {

    // 绑定服务到注册服务上
    public void regionService(ServerNode serverNode);
}
