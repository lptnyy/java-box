package com.wzy.action;

import com.wzy.action.parameter.user.Login;
import com.wzy.util.annotation.factory.Verification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoxUserController {

    @RequestMapping(value = "/login")
    public String login(Login login) {
        return Verification.verification(login).setBusiness(jsonVo -> {

            return jsonVo;
        }).init().returnJsonString();
    }
}
