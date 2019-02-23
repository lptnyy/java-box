package com.demo;
import com.alibaba.fastjson.JSON;
import com.demo.sql.BoxUser;
import com.demo.sql.UserDao;
import com.wzy.func.annotation.BoxApi;
import com.wzy.func.annotation.BoxApp;
import com.wzy.func.annotation.BoxSetBean;
import com.wzy.func.fc.IBoxCache;
import com.wzy.func.fc.IBoxDataSource;
import com.wzy.func.fc.IBoxHttpRequest;
import com.wzy.func.fc.IBoxHttpResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@BoxApp(name = "demo", path = "/demo")
public class UserZdyWork {

    @BoxSetBean(value = "com.wzy.plug.db.druid.BoxDruidDataSource")
    IBoxDataSource boxDataSource;

    @BoxSetBean(value = "com.wzy.plug.ceche.redis.BoxRedisCacheSource")
    IBoxCache boxCache;

    @BoxApi(name = "demo", path = "/demo")
    public boolean demo(IBoxHttpRequest request, IBoxHttpResponse response) {
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

    @BoxApi(name = "demo", path = "/demo1")
    public boolean demo1(IBoxHttpRequest request, IBoxHttpResponse response) {
        DataSource dataSource = boxDataSource.dataSource();
        try {
            boxCache.set("keys","123");
            response.print(request.getChx(), boxCache.get("keys"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
