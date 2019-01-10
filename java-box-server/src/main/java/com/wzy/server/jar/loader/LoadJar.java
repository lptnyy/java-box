package com.wzy.server.jar.loader;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;

import java.util.List;

public interface LoadJar {
    public boolean runClass(BoxHttpRequest request, BoxHttpResponse response) throws Exception;
    public void initAppHttp(Integer appId) throws Exception;
    public void initAppHttp(List<String> appIds);
    public void initFliterHttp(Integer id);
    public void initFliterHttp(List<String> ids);
    public boolean scanJar();
}
