package com.demo;
import com.alibaba.fastjson.JSON;
import com.demo.sql.BoxUser;
import com.demo.sql.UserDao;
import com.wzy.func.annotation.BoxApi;
import com.wzy.func.annotation.BoxApp;
import com.wzy.func.annotation.BoxSetBean;
import com.wzy.func.fc.IBoxDataSource;
import com.wzy.func.fc.IBoxHttpRequest;
import com.wzy.func.fc.IBoxHttpResponse;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@BoxApp(name = "demo", path = "/demo")
public class UserZdyWork {

    @BoxSetBean
    IBoxDataSource boxDataSource;

    @BoxApi(name = "demoapi4", path = "/demo")
    public boolean getTest(IBoxHttpRequest request, IBoxHttpResponse response) {
        DataSource dataSource = boxDataSource.dataSource();
        try {
            Connection connection = dataSource.getConnection();
            UserDao userDao = new UserDao();
            List<BoxUser> users =userDao.getUserList(connection);
            response.print(request.getChx(), JSON.toJSONString(users));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
