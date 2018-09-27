package com.wzy.server.http.request;

import com.wzy.server.http.filter.HttpFilter;
import com.wzy.server.http.filter.HttpFiterImpl;
import com.wzy.server.http.response.BoxHttpResponseImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;

import java.io.IOException;

public class RequestParser {
    private  HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);
    private HttpObject fullReq;
    private ChannelHandlerContext chx;
    private HttpFilter filter = new HttpFiterImpl();

    /**
     * 构造一个解析器
     * @param req
     */
    public RequestParser(HttpObject req, ChannelHandlerContext chx) {
        this.fullReq = req;
        this.chx = chx;
    }

    /**
     * 解析请求参数
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     *
     * @throws IOException
     */
    public void parse() throws IOException {
        BoxHttpResponseImpl boxHttpResponse = new BoxHttpResponseImpl();
        BoxHttpRequestImpl boxHttpRequest = new BoxHttpRequestImpl();
        boxHttpRequest.setChx(chx);
        HttpRequest request = (HttpRequest)fullReq;
        HttpMethod method = request.method();
        request.headers().forEach(v -> {
            boxHttpRequest.setHeader(v.getKey(),v.getValue());
        });
        if (HttpMethod.GET == method) {
            boxHttpRequest.setMethod("get");
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                boxHttpRequest.setParameter(entry.getKey(), entry.getValue().get(0));
            });
            boxHttpRequest.setUri(request.uri());

        } else if (HttpMethod.POST == method) {
            boxHttpRequest.setUri(request.uri());
            boxHttpRequest.setMethod("post");
            // 是POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(factory, request);
            if (decoder != null) {
                if (fullReq instanceof HttpContent) {
                    HttpContent chunk = (HttpContent) request;
                    decoder.offer(chunk);
                    try{
                        while (decoder.hasNext()) {
                            InterfaceHttpData data = decoder.next();
                            if (data != null) {
                                switch (data.getHttpDataType()) {
                                    case Attribute:
                                        Attribute attribute = (Attribute) data;
                                        boxHttpRequest.setParameter(attribute.getName(),attribute.getValue());
                                        break;
                                    case FileUpload:
                                        FileUpload fileUpload = (FileUpload) data;
                                        break;
                                }
                                data.release();
                            }
                        }
                    }catch (Exception e){

                    } finally {

                    }
                }
            }
        }
        if (filter.init(chx,boxHttpRequest,boxHttpResponse)){
            filter.service(chx,boxHttpRequest,boxHttpResponse);
            filter.release();
        }
    }
}
