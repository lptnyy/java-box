package com.wzy.func.fc;

import io.netty.channel.ChannelHandlerContext;

public interface IBoxHttpResponse {
    public void print(ChannelHandlerContext chx, String value);
}
