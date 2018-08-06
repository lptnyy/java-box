package com.wzy.server.filter;

import com.wzy.server.request.BoxHttpRequest;
import com.wzy.server.response.BoxHttpResponse;

public class HttpFiterImpl implements HttpFilter {

    @Override
    public boolean init(BoxHttpRequest request, BoxHttpResponse response) {
        return true;
    }

    @Override
    public void service(BoxHttpRequest request, BoxHttpResponse response) {

    }

    @Override
    public void release() {

    }
}
