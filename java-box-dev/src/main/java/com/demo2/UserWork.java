package com.demo2;
import com.wzy.server.http.request.BoxHttpRequest;
import com.wzy.server.http.response.BoxHttpResponse;
import com.wzy.server.work.BoxWork;
public class UserWork implements BoxWork{

    @Override
    public boolean run(BoxHttpRequest request, BoxHttpResponse response) {
        response.print(request.getChx(), "hahahaahahahaahahahaa");
        return false;
    }
}
