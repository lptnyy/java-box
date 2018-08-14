package com.wzy.server.http.response;

import io.netty.channel.ChannelHandlerContext;

public interface BoxHttpResponse {
    public void print(ChannelHandlerContext chx, String value);
}
