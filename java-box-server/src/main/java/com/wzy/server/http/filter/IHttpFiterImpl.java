package com.wzy.server.http.filter;
import com.wzy.func.fc.*;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.http.request.HttpCodePrint;
import com.wzy.server.jar.loader.LoadJarImpl;
import io.netty.channel.ChannelHandlerContext;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
public class IHttpFiterImpl implements IHttpFilter {
    ILog log = BoxLog.getInstance();
    ILoadJar loadJar = LoadJarImpl.getInstance();
    static IHttpFilter IHttpFilter;

    public static IHttpFilter getInstance() {
        if (IHttpFilter == null) {
            synchronized (ILoadJar.class) {
                if (IHttpFilter == null) {
                    IHttpFilter = new IHttpFiterImpl();
                }
            }
        }
        return IHttpFilter;
    }

    @Override
    public boolean service(ChannelHandlerContext chx, IBoxHttpRequest request, IBoxHttpResponse response) {
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
