package com.demo;
import com.alibaba.fastjson.JSON;
import com.demo.sql.BoxUser;
import com.demo.sql.UserDao;
import com.wzy.func.annotation.BoxApi;
import com.wzy.func.annotation.BoxApp;
import com.wzy.func.annotation.BoxInit;
import com.wzy.func.fc.BoxHttpRequest;
import com.wzy.func.fc.BoxHttpResponse;

import java.util.List;

@BoxApp(name = "demo", path = "/demo")
public class UserZdyWork {

    @BoxApi(name = "demoapi4", path = "/demo")
    public boolean getTest(BoxHttpRequest request, BoxHttpResponse response) {
        UserDao userDao = new UserDao();
        List<BoxUser> boxUserList = userDao.getUserList();
        response.print(request.getChx(), JSON.toJSONString(boxUserList));
        return true;
    }
    
    /**
     * 初始化部分调用过程中只执行一次
     */
    @BoxInit
    public void init(){
        System.out.println("init");
    }
}
