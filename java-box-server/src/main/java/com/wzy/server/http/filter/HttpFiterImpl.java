package com.wzy.server.http.filter;
import com.wzy.server.config.Config;
import com.wzy.server.http.monitor.HttpMonitor;
import com.wzy.server.http.monitor.HttpMonitorImpl;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.request.HttpCodePrint;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.api.config.BoxFilterRun;
import io.netty.channel.ChannelHandlerContext;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
public class HttpFiterImpl implements HttpFilter {
    HttpMonitor httpMonitor = new HttpMonitorImpl();

    @Override
    public boolean service(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {
        try {
            long startTimes = System.currentTimeMillis();
            if(Config.loadJar.runFliter(request,response).getCode() == BoxFilterRun.RUNSU)
                return false;
            if(Config.loadJar.runClass(request,response)){
            } else {
                HttpCodePrint.sendError(chx, NOT_FOUND);
            };
            long endTimes = System.currentTimeMillis();
            request.setRunTime(endTimes-startTimes);
            httpMonitor.monitor(chx,request,response);
            return true;
        } catch (Exception e) {
            HttpCodePrint.sendError(chx, INTERNAL_SERVER_ERROR);
            Config.log.error(e);
        }
        return false;
    }
}
