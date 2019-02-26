package com.demo;
import com.alibaba.fastjson.JSON;
import com.demo.sql.BoxUser;
import com.demo.sql.UserDao;
import com.wzy.func.annotation.BoxApi;
import com.wzy.func.annotation.BoxApp;
import com.wzy.func.annotation.BoxSetBean;
import com.wzy.func.fc.IBoxCache;
import com.wzy.func.fc.IBoxDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
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
    public boolean demo(HttpServletRequest request, HttpServletResponse response) {
        DataSource dataSource = boxDataSource.dataSource();
        try {
            Connection connection = dataSource.getConnection();
            UserDao userDao = new UserDao();
            List<BoxUser> users =userDao.getUserList(connection);
            printJson(response,JSON.toJSONString(users));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @BoxApi(name = "demo", path = "/demo1")
    public boolean demo1(HttpServletRequest request, HttpServletResponse response) {
        DataSource dataSource = boxDataSource.dataSource();
        try {
            boxCache.set("keys","123");
            printJson(response,boxCache.get("keys"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void printJson(HttpServletResponse response, String jsonStr){
        try {
            PrintWriter out = response.getWriter();
            out.print(jsonStr);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
