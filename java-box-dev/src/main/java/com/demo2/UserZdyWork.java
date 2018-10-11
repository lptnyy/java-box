package com.demo2;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.annotation.BoxApi;
import com.wzy.server.jar.annotation.BoxApp;

@BoxApp(name = "demo", route = "/demo")
public class UserZdyWork {

    @BoxApi(name = "demoapi", route = "/demo")
    public boolean getTest(BoxHttpRequest request, BoxHttpResponse response) {
       response.print(request.getChx(), "demo");
       return true;
    }

    @BoxApi(name="demolistapi", route = "/demolist")
    public boolean test(BoxHttpRequest request, BoxHttpResponse response){
        response.print(request.getChx(), "demolist");
        return true;
    }
}
