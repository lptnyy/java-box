package com.wzy.work;

import com.wzy.server.request.BoxHttpRequest;
import com.wzy.server.response.BoxHttpResponse;

public interface BoxWork {
    public boolean run(BoxHttpRequest request, BoxHttpResponse response);
}
