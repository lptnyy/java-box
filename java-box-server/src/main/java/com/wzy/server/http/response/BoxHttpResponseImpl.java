package com.wzy.server.http.response;

import com.wzy.server.config.Config;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderValues;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class BoxHttpResponseImpl implements BoxHttpResponse {

    @Override
    public void print(ChannelHandlerContext chx, String value) {
        FullHttpResponse response = null;
        byte[] datas = null;
        try {
            datas = value.getBytes(Config.config.getCharSet());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(datas));
        response.headers().set(CONTENT_TYPE, "text/html");
        response.headers().set(CONTENT_LENGTH,response.content().readableBytes());
        response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        chx.write(response).addListener(ChannelFutureListener.CLOSE);
        chx.flush();
    }
}
