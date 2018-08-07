package com.wzy.server.filter;

import com.wzy.server.request.BoxHttpRequest;
import com.wzy.server.response.BoxHttpResponse;
import io.netty.channel.ChannelHandlerContext;

public class HttpFiterImpl implements HttpFilter {


    @Override
    public boolean init(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {
        return true;
    }

    @Override
    public void service(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {
        response.print(chx,"hhahaa");
    }

    @Override
    public void release() {

    }
}
