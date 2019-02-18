package com.wzy.func.fc;

import io.netty.channel.ChannelHandlerContext;

public interface IHttpFilter {

    /**
     * 拦截器配置
     * @param chx
     * @param request
     * @param response
     */
    public boolean service(ChannelHandlerContext chx, IBoxHttpRequest request, IBoxHttpResponse response);
}
