package com.wzy.server.work.proxy;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.work.BoxWork;

/**
 * 创建work静态代理类
 */
public class BoxWorkProxy {
    public BoxWork boxWork;
    public BoxWorkProxy(BoxWork boxWork){
        this.boxWork = boxWork;
    }
    public boolean invoke(BoxHttpRequest request, BoxHttpResponse response) throws Exception {
        return boxWork.run(request,response);
    }
}
