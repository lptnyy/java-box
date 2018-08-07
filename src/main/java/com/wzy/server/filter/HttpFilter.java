package com.wzy.server.filter;

import com.wzy.server.request.BoxHttpRequest;
import com.wzy.server.response.BoxHttpResponse;
import io.netty.channel.ChannelHandlerContext;

public interface HttpFilter {
    public boolean init(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response);
    public void service(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response);
    public void release();
}
