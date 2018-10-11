package com.demo2;

import com.alibaba.fastjson.JSON;
import com.demo2.sql.UserDao;
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

    @BoxApi(name = "查询sql", route = "/sqltest")
    public boolean sqlTest(BoxHttpRequest request, BoxHttpResponse response){
        UserDao userDao = new UserDao();
        String result = JSON.toJSONString(userDao.getUserList());
        response.print(request.getChx(), result);
        return true;
    }
}
