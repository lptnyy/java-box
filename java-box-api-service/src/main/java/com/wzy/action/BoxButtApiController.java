package com.wzy.action;
import com.wzy.service.ButtApiService;
import com.wzy.util.BaseController;
import com.wzy.util.jsonvo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * 服务对接接口
 */
@RestController
public class BoxButtApiController extends BaseController {

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

    /**
     * 下载jar文件
     * @param downUrl
     * @return
     */
    @RequestMapping(value = "/downJar")
    public ResponseEntity<FileSystemResource> listExport(String downUrl) {
        String baseUrl = System.getProperty("user.dir");
        File file = new File(baseUrl+downUrl);
        return export(file);
    }
}
