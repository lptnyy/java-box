package com.wzy.util.adapter;

import com.wzy.service.UserServiceI;
import com.wzy.util.jsonvo.JsonVo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorConfig implements HandlerInterceptor {
    UserServiceI userServiceI;
    public InterceptorConfig(UserServiceI serviceI){
        this.userServiceI = serviceI;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String tokenUserId = httpServletRequest.getParameter("tokenUserId");
        String token = httpServletRequest.getParameter("token");
        boolean op = userServiceI.checkUserToken(tokenUserId,token);
        if (op) {
            return true;
        } else {
            httpServletResponse.getWriter().print(new JsonVo().setBody("token_error",false).returnJsonString());
            httpServletResponse.getWriter().close();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
