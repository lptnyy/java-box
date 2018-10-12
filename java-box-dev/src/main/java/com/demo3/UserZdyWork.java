package com.demo3;

import com.alibaba.fastjson.JSON;
import com.demo3.sql.UserDao;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.annotation.BoxApi;
import com.wzy.server.jar.annotation.BoxApp;

@BoxApp(name = "demo4", route = "/demo4")
public class UserZdyWork {

    @BoxApi(name = "demoapi4", route = "/demo4")
    public boolean getTest(BoxHttpRequest request, BoxHttpResponse response) {
       response.print(request.getChx(), request.getParameter("name").toString());
       return true;
    }
}
