package com.wzy.action;

import com.wzy.server.jar.loader.BoxUrlClassLoader;
import com.wzy.server.jar.loader.config.Jar;
import com.wzy.server.jar.loader.config.ScanJar;
import com.wzy.service.ButtApiService;
import com.wzy.util.BaseController;
import com.wzy.util.exception.MyExceptionUtil;
import com.wzy.util.jsonvo.JsonVo;
import com.wzy.util.upload.FileVo;
import com.wzy.util.upload.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("user")
public class UploadDownFileController extends BaseController{
    @Autowired
    ButtApiService buttApiService;

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadJar", method = RequestMethod.POST)
    public String uploadJar(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String option = request.getMethod();
        return new JsonVo()
                .setResult(true)
                .setBusiness(jsonVo -> {
                    try{
                        jsonVo.setObject(UploadUtil.uploadRandomFile(file));
                    }catch (Exception e){
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadPlJar", method = RequestMethod.POST)
    public String uploadPlJar(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String option = request.getMethod();
        return new JsonVo()
                .setResult(true)
                .setBusiness(jsonVo -> {
                    try{
                        FileVo fileVo = UploadUtil.uploadRandomFile(file);
                        String baseUtl = System.getProperty("user.dir");
                        String dpwnUrl = baseUtl+fileVo.getFileUrl();
                        Jar jarVo = new Jar();
                        jarVo.setJarDownUrl("file:"+dpwnUrl);
                        ScanJar scanJar = BoxUrlClassLoader.scanJar(jarVo);
                        buttApiService.addProject(scanJar, fileVo);
                    }catch (Exception e){
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
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
