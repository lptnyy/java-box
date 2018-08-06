package com.wzy.server.request.util;

import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParserUtil {

    private  HttpDataFactory factory =
            new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);
    private HttpObject fullReq;

    /**
     * 构造一个解析器
     * @param req
     */
    public RequestParserUtil(HttpObject req) {
        this.fullReq = req;
    }

    /**
     * 解析请求参数
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     *
     * @throws IOException
     */
    public Map<String, Object> parse() throws IOException {
        HttpRequest request = (HttpRequest)fullReq;
        HttpMethod method = request.method();
        Map<String, Object> parmMap = new HashMap<>();
        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });
        } else if (HttpMethod.POST == method) {
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
                                        parmMap.put(attribute.getName(),attribute.getValue());
                                        break;
                                    case FileUpload:
                                        FileUpload fileUpload = (FileUpload) data;
                                        parmMap.put(data.getName(), fileUpload.content());
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
        } else {

        }
        return parmMap;
    }
}
