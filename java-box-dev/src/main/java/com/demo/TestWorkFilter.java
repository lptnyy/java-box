package com.demo;

import com.wzy.func.fc.BoxHttpRequest;
import com.wzy.func.fc.BoxHttpResponse;
import com.wzy.func.fc.WorkFilter;
import com.wzy.func.annotation.BoxWorkFilter;

@BoxWorkFilter(name = "测试过滤器", path = "/demo/*")
public class TestWorkFilter implements WorkFilter {

    @Override
    public boolean service(BoxHttpRequest request, BoxHttpResponse response) {
        System.out.println("Filter true");
        response.print(request.getChx(),"error");
        return false;
    }
}
