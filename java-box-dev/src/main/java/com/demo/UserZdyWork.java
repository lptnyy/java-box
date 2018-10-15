package com.demo;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.annotation.BoxApi;
import com.wzy.server.jar.annotation.BoxApp;

@BoxApp(name = "demo", route = "/demo")
public class UserZdyWork {

    @BoxApi(name = "demoapi4", route = "/demo")
    public boolean getTest(BoxHttpRequest request, BoxHttpResponse response) {
       response.print(request.getChx(), request.getParameter("name").toString());
       return true;
    }
}
