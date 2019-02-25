package com.wzy.box.server.handle;
import com.wzy.func.fc.BoxFilterRun;
import com.wzy.func.fc.ILoadJar;
import com.wzy.server.jar.loader.LoadJarImpl;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;

@Component
public class BoxUrlFilter implements Filter{
    ILoadJar iLoadJar = LoadJarImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            long startTimes = System.currentTimeMillis();
            if(iLoadJar.runFliter(request,response).getCode() == BoxFilterRun.RUNSU) {

            }
            if(iLoadJar.runClass(request,response)){

            } else {

            };
            long endTimes = System.currentTimeMillis();
        } catch (Exception e) {

        }
    }

    @Override
    public void destroy() {

    }
}
