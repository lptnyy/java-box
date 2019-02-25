package com.wzy.func.fc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHttpWorkFilter {

    /**
     * 过滤器接口
     * @param request
     * @param response
     * @return
     */
    public boolean service(HttpServletRequest request, HttpServletResponse response);
}
