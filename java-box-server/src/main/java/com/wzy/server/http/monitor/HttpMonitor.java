package com.wzy.server.http.monitor;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import io.netty.channel.ChannelHandlerContext;

/**
 * 监控配置
 */
public interface HttpMonitor {

    // 监控拦截器
    public void monitor(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response);
}
