package com.demo;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.annotation.BoxApi;
import com.wzy.server.jar.annotation.BoxApp;

@BoxApp(name = "test", route = "/test")
public class UserZdyWork {

    @BoxApi(name = "getuser", route = "/getuserlist")
    public boolean getTest(BoxHttpRequest request, BoxHttpResponse response) {
       return false;
    }

    @BoxApi(name="測試數據", route = "/test123")
    public boolean test(BoxHttpRequest request, BoxHttpResponse response){
        return false;
    }
}
