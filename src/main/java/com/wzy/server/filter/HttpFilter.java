package com.wzy.server.filter;

import com.wzy.server.request.BoxHttpRequest;
import com.wzy.server.response.BoxHttpResponse;

public interface HttpFilter {
    public boolean init(BoxHttpRequest request, BoxHttpResponse response);
    public void service(BoxHttpRequest request, BoxHttpResponse response);
    public void release();
}
