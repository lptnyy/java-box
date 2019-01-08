package com.demo;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.jar.annotation.BoxApi;
import com.wzy.server.jar.annotation.BoxApp;
import com.wzy.server.jar.annotation.BoxInit;

@BoxApp(name = "demo", path = "/demo")
public class UserZdyWork {

    @BoxApi(name = "demoapi4", path = "/demo")
    public boolean getTest(BoxHttpRequest request, BoxHttpResponse response) {
       response.print(request.getChx(), request.getParameter("name").toString());
       return true;
    }

    /**
     * 初始化部分调用过程中只执行一次
     */
    @BoxInit
    public void init(){

    }
}
