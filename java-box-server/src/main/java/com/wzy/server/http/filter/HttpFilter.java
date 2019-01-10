package com.wzy.server.http.filter;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import io.netty.channel.ChannelHandlerContext;

public interface HttpFilter {

    /**
     * 拦截器配置
     * @param chx
     * @param request
     * @param response
     */
    public boolean service(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response);
}
