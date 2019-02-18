package com.wzy.func.fc;

public interface WorkFilter {

    /**
     * 过滤器接口
     * @param request
     * @param response
     * @return
     */
    public boolean service(IBoxHttpRequest request, IBoxHttpResponse response);
}
