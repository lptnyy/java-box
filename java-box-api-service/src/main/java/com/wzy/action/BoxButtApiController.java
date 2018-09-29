package com.wzy.action;

import com.wzy.service.ButtApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务对接接口
 */
@RestController
public class BoxButtApiController {

    @Autowired
    ButtApiService apiService;

}
