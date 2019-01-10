package com.demo;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.annotation.BoxWorkFilter;
import com.wzy.server.jar.fuc.WorkFilter;

@BoxWorkFilter(name = "测试过滤器", path = "/demo/*")
public class TestWorkFilter implements WorkFilter {

    @Override
    public boolean service(BoxHttpRequest request, BoxHttpResponse response) {
        System.out.println("Filter true");
        return true;
    }
}
