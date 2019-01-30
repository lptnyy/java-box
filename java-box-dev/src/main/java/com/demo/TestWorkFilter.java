package com.demo;

import com.wzy.func.fc.BoxHttpRequest;
import com.wzy.func.fc.BoxHttpResponse;
import com.wzy.func.fc.WorkFilter;
import com.wzy.func.annotation.BoxWorkFilter;
import org.apache.zookeeper.KeeperException;

@BoxWorkFilter(name = "测试过滤器", path = "/demo/*")
public class TestWorkFilter implements WorkFilter {

    @Override
    public boolean service(BoxHttpRequest request, BoxHttpResponse response) {
        try {
            System.out.println(request.getConfig().getValue("123"));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.print(request.getChx(),"error");
        return true;
    }
}
