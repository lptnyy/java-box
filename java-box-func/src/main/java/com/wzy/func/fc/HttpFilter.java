package com.wzy.func.fc;

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
