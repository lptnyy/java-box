package com.wzy.server.filter;

import com.wzy.server.request.BoxHttpRequestImpl;
import com.wzy.server.response.BoxHttpResponseImpl;

public interface HttpFilter {
    public void init(BoxHttpRequestImpl request, BoxHttpResponseImpl response);
    public void service(BoxHttpRequestImpl request, BoxHttpResponseImpl response);
    public void release();
}
