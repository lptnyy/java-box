package com.wzy.func.fc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ILoadJar {
    public boolean runClass(IBoxHttpRequest request, IBoxHttpResponse response) throws Exception;
    public BoxFilterRun runFliter(IBoxHttpRequest request, IBoxHttpResponse response)  throws Exception;
    public boolean runClass(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
    public BoxFilterRun runFliter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)  throws Exception;
    public void initAppHttp(Integer appId) throws Exception;
    public void initAppHttp(List<String> appIds);
    public void initFliterHttp(String id);
    public void initFliterHttp(List<String> ids);
    public void initConnectPool(String ids);
    public void removeConnectPool(String id);
    public void removeFliterHttp(String id);
    public boolean scanJar();
}
