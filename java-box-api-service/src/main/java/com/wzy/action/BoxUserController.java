package com.wzy.action;

import com.wzy.action.parameter.user.Login;
import com.wzy.entity.vo.BoxUserVo;
import com.wzy.service.UserServiceI;
import com.wzy.util.annotation.factory.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoxUserController {

    @Autowired
    UserServiceI userServiceI;

    @RequestMapping(value = "/login")
    public String login(Login login) {
        return Verification.verification(login).setJsonp(login.getJsonp()).setBusiness(jsonVo -> {
            try{
                BoxUserVo user = userServiceI.checkUser(login.getUser_name(), login.getUser_pass());
                if (user == null) {
                    jsonVo.setBody("账户/密码不正确", false);
                } else {
                    user.setUser_name("");
                    user.setUser_pass("");
                    jsonVo.setObject(user);
                }
            }catch (Exception e){
                jsonVo.setBody(e.getMessage(), false);
            }
            return jsonVo;
        }).init().returnJsonString();
    }
}
