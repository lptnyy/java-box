package com.wzy.work;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;

public interface BoxWork {

    /**
     * work 执行函数
     * @param request
     * @param response
     * @return
     */
    public boolean run(BoxHttpRequest request, BoxHttpResponse response);
}
