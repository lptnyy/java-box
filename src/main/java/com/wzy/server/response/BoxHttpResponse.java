package com.wzy.server.response;

import io.netty.channel.ChannelHandlerContext;

public interface BoxHttpResponse {
    public void print(ChannelHandlerContext chx, String value);
}
