package com.wzy.action;
import com.wzy.service.ButtApiService;
import com.wzy.util.jsonvo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务对接接口
 */
@RestController
public class BoxButtApiController {

    @Autowired
    ButtApiService apiService;

    /**
     * 容器查询应用列表
     * @return
     */
    @RequestMapping(path = "/butt/getApplist")
    public String getAppList(){
        return new JsonVo().setResult(true).setBusiness(jsonVo -> {
            try{
                jsonVo.setObject(apiService.getBoxApp());
            } catch (Exception e) {
                jsonVo.setBody(e.getMessage(),  false);
            }
            return jsonVo;
        }).init().returnJsonString();
    }

    /**
     * 容器查询应用列表下的api信息
     * @param appId
     * @return
     */
    @RequestMapping(path = "/butt/getAppApiList")
    public String getAppApiList(Integer appId){
        return new JsonVo().setResult(true).setBusiness(jsonVo -> {
            try{
                jsonVo.setObject(apiService.getBoxAppApi(appId));
            }catch (Exception e) {
                jsonVo.setBody(e.getMessage(), false);
            }
            return jsonVo;
        }).init().returnJsonString();
    }
}
