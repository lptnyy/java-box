package com.wzy.server.jar.loader;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.loader.LoadJar;

public class LoadJarImpl implements LoadJar {

    @Override
    public boolean runClass(BoxHttpRequest request, BoxHttpResponse response) throws Exception {
        return false;
    }

    @Override
    public void initJar() throws Exception {

    }

    @Override
    public void initHttp() throws Exception {

    }

    @Override
    public boolean scanJar() {
        return false;
    }
}
