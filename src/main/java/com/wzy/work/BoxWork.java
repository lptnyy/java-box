package com.wzy.work;

import com.wzy.server.request.BoxHttpRequest;
import com.wzy.server.response.BoxHttpResponse;

public interface BoxWork {

    /**
     * work 执行函数
     * @param request
     * @param response
     * @return
     */
    public boolean run(BoxHttpRequest request, BoxHttpResponse response);
}
