package com.demo;

import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.annotation.BoxApi;
import com.wzy.server.jar.annotation.BoxModule;
import com.wzy.server.jar.annotation.BoxProject;

@BoxProject(name = "test", route = "/test")
public class UserZdyWork {

    // 自定义方法必须是这样格式的 并且返回是否执行成功 boolean 方法参数固定两个参数
    @BoxModule(name = "user", route = "/user")
    @BoxApi(name = "getuser", route = "/getuserlist")
    public boolean getTest(BoxHttpRequest request, BoxHttpResponse response) {
       return false;
    }
}
