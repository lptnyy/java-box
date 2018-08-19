package com.wzy.server.jar.work;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;

public interface BoxWorkFilter {

    // filter 初始化 执行开始
    public boolean init(BoxHttpRequest request, BoxHttpResponse response);

    // 执行filter 执行中
    public void service(BoxHttpRequest request, BoxHttpResponse response);

    // 回收 执行结束
    public void release();
}
