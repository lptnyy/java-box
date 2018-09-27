package com.wzy.server.http.monitor;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import io.netty.channel.ChannelHandlerContext;

public class HttpMonitorImpl implements HttpMonitor{
    static HttpMonitor httpMonitor = null;

    private HttpMonitorImpl(){

    }

    public static synchronized HttpMonitor getHttpMonitor(){
        if (httpMonitor==null){
            httpMonitor = new HttpMonitorImpl();
        }
        return httpMonitor;
    }

    @Override
    public void monitor(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {

    }
}
