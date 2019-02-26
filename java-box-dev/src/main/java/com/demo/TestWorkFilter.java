package com.demo;
import com.wzy.func.fc.IHttpWorkFilter;
import com.wzy.func.annotation.BoxWorkFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@BoxWorkFilter(name = "测试过滤器", path = "/demo/*")
public class TestWorkFilter implements IHttpWorkFilter {

    @Override
    public boolean service(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
}
