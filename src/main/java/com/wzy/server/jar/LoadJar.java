package com.wzy.server.jar;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.api.vo.BoxApiVo;
import com.wzy.server.jar.api.vo.BoxMoudulaVo;
import com.wzy.server.jar.api.vo.BoxProjectVo;

import java.util.List;
import java.util.Map;

public interface LoadJar {
    public boolean runClass(BoxHttpRequest request, BoxHttpResponse response) throws Exception;
    public void initJar() throws Exception;
    public void initHttp() throws Exception;
    public List<BoxProjectVo> getProjectMaps();
    public List<BoxMoudulaVo> getMdudulaVoMaps();
    public List<BoxApiVo> getApiVoMaps();
}