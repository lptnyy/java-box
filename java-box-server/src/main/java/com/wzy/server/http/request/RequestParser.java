package com.wzy.server.http.request;

import com.wzy.func.fc.IHttpFilter;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.StartServer;
import com.wzy.server.http.filter.IHttpFiterImpl;
import com.wzy.server.http.response.IBoxHttpResponseImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class RequestParser {
    ILog log = BoxLog.getInstance();
    private HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);
    private IHttpFilter filter = IHttpFiterImpl.getInstance();
    static RequestParser requestParser;
    public RequestParser(){
        System.out.println("1");
    }

    /**
     * 单例模式
     * @return
     */
    public static RequestParser getInstance(){
        if (requestParser == null) {
            synchronized (RequestParser.class) {
                if (requestParser == null) {
                    requestParser = new RequestParser();
                }
            }
        }
        return requestParser;
    }


    /**
     * 解析请求参数
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     *
     * @throws IOException
     */
    public void parse(HttpObject fullReq, ChannelHandlerContext chx) throws IOException {
        IBoxHttpResponseImpl boxHttpResponse = new IBoxHttpResponseImpl();
        IBoxHttpRequestImpl boxHttpRequest = new IBoxHttpRequestImpl();
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
            boxHttpRequest.setUri(decoder.uri());

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
                                        writeChunk(fileUpload);
                                        break;
                                }
                                data.release();
                            }
                        }
                    }catch (Exception e){
                        log.error(e);
                    } finally {

                    }
                }
            }
        }
        if (boxHttpRequest.uri().indexOf("?") !=-1){
            boxHttpRequest.setUri(boxHttpRequest.uri().substring(0,boxHttpRequest.uri().lastIndexOf("?")));
        }
        boxHttpRequest.setConifg(StartServer.zkNetConfig);
        filter.service(chx,boxHttpRequest,boxHttpResponse);
    }

    private void writeChunk(FileUpload fileUpload) throws IOException {
        File file = new File("C:\\test\\" + fileUpload.getFilename());
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileChannel inputChannel = new FileInputStream(fileUpload.getFile()).getChannel();
                     FileChannel outputChannel = new FileOutputStream(file).getChannel()) {
                    outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (Exception e) {

        }
    }
}
