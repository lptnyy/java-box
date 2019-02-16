package com.wzy.func.fc;

import java.util.List;

public interface ILoadJar {
    public boolean runClass(BoxHttpRequest request, BoxHttpResponse response) throws Exception;
    public void initAppHttp(Integer appId) throws Exception;
    public void initAppHttp(List<String> appIds);
    public BoxFilterRun runFliter(BoxHttpRequest request, BoxHttpResponse response)  throws Exception;
    public void initFliterHttp(String id);
    public void initFliterHttp(List<String> ids);
    public void initConnectPool(String ids);
    public void removeConnectPool(String id);
    public void removeFliterHttp(String id);
    public boolean scanJar();
}
