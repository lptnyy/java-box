package com.wzy.server.filter;

import com.wzy.config.Config;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.request.HttpCode;
import com.wzy.server.http.response.BoxHttpResponse;
import io.netty.channel.ChannelHandlerContext;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
public class HttpFiterImpl implements HttpFilter {

    @Override
    public boolean init(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {
        return true;
    }

    @Override
    public void service(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {
        try {
            if(Config.loadJar.runClass(request,response)){

            } else {

            };
        } catch (Exception e) {
            HttpCode.sendError(chx, NOT_FOUND);
            Config.log.error(e);
        }
    }

    @Override
    public void release() {

    }
}
