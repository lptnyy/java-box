package com.wzy.server.jar.fuc;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;

public interface WorkFilter {

    /**
     * 过滤器接口
     * @param request
     * @param response
     * @return
     */
    public boolean service(BoxHttpRequest request, BoxHttpResponse response);
}
