package com.wzy.server.http.filter;
import com.wzy.func.fc.*;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.http.request.HttpCodePrint;
import com.wzy.server.jar.loader.LoadJarImpl;
import io.netty.channel.ChannelHandlerContext;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
public class HttpFiterImpl implements HttpFilter {
    ILog log = BoxLog.getInstance();
    ILoadJar loadJar = LoadJarImpl.getInstance();

    @Override
    public boolean service(ChannelHandlerContext chx, BoxHttpRequest request, BoxHttpResponse response) {
        try {
            long startTimes = System.currentTimeMillis();
            if(loadJar.runFliter(request,response).getCode() == BoxFilterRun.RUNSU)
                return false;
            if(loadJar.runClass(request,response)){
            } else {
                HttpCodePrint.sendError(chx, NOT_FOUND);
            };
            long endTimes = System.currentTimeMillis();
            request.setRunTime(endTimes-startTimes);
            return true;
        } catch (Exception e) {
            HttpCodePrint.sendError(chx, INTERNAL_SERVER_ERROR);
            log.error(e);
        }
        return false;
    }
}
