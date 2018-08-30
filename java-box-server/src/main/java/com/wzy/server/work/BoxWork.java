package com.wzy.server.work;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;

/**
 * 客户端开发库
 */
public interface BoxWork {
    public boolean run(BoxHttpRequest request, BoxHttpResponse response);
}
