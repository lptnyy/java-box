package com.wzy.server.http.filter;
import com.wzy.server.config.Config;
import com.wzy.server.http.monitor.HttpMonitor;
import com.wzy.server.http.monitor.HttpMonitorImpl;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.request.HttpCodePrint;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.util.time.DateUtil;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
public class HttpFiterImpl implements HttpFilter {
    HttpMonitor httpMonitor = HttpMonitorImpl.getHttpMonitor();

    @Override
    public boolean init(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {
        return true;
    }

    @Override
    public void service(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {
        try {
            Config.log.visit("url:"+request.uri()+",times:"+ DateUtil.getyyMMddHHmmss(new Date()));
            if(Config.loadJar.runClass(request,response)){

            } else {
                HttpCodePrint.sendError(chx, NOT_FOUND);
            };
        } catch (Exception e) {
            HttpCodePrint.sendError(chx, NOT_FOUND);
            Config.log.error(e);
        }
    }

    @Override
    public void release() {

    }
}
