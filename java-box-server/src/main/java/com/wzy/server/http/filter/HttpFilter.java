package com.wzy.server.http.filter;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import io.netty.channel.ChannelHandlerContext;

public interface HttpFilter {
    public boolean init(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response);
    public void service(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response);
    public void release();
}
