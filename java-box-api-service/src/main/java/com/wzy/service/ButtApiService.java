package com.wzy.service;
import com.wzy.entity.*;
import com.wzy.entity.vo.BoxAppVo;
import com.wzy.mapper.*;
import com.wzy.mapper.table.TabBoxApp;
import com.wzy.server.jar.loader.config.ScanJar;
import com.wzy.util.DateUtil;
import com.wzy.util.PageUtil;
import com.wzy.util.upload.FileVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ButtApiService{

    @Resource
    BoxAppMapper boxAppMapper;
    @Resource
    BoxAppApiMapper boxAppApiMapper;

    /**
     * 通过上传添加应用信息，通过扫描注解生成项目信息
     * @param scanJar
     * @param fileVo
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addProject(ScanJar scanJar, FileVo fileVo) throws Exception{
        scanJar.getBoxProjectVo().forEach(boxProjectVo -> {

            // 查看項目是否存在不存在插入新數據
            BoxApp boxApp = boxAppMapper.getRouteApp(boxProjectVo.getRoute());
            if (boxApp == null) {
                boxApp = new BoxApp();
                boxApp.setJarMd5(fileVo.getFileMd5());
                boxApp.setJarName(fileVo.getFileName());
                boxApp.setJarUrl(fileVo.getFileUrl());
                boxApp.setName(boxProjectVo.getProjectName());
                boxApp.setRoute(boxProjectVo.getRoute());
                boxApp.setStats(0);
                boxAppMapper.add(boxApp);
            } else {
                boxApp.setJarMd5(fileVo.getFileMd5());
                boxApp.setJarName(fileVo.getFileName());
                boxApp.setJarUrl(fileVo.getFileUrl());
                boxApp.setName(boxProjectVo.getProjectName());
                boxApp.setRoute(boxProjectVo.getRoute());
                boxAppMapper.update(boxApp);
            }

            // 生成访问接口信息
            BoxApp finalBoxApp = boxApp;
            scanJar.getBoxApiVoList().forEach(boxApiVo -> {
                BoxAppApi boxAppApi = boxAppApiMapper.getRouteAppApi(boxApiVo.getApiRoute(), finalBoxApp.getAppId().toString());
                if (boxAppApi == null) {
                    boxAppApi = new BoxAppApi();
                    boxAppApi.setAppId(finalBoxApp.getAppId());
                    boxAppApi.setJarMd5(finalBoxApp.getJarMd5());
                    boxAppApi.setStats(0);
                    boxAppApi.setName(boxApiVo.getApiName());
                    boxAppApi.setRoute(boxApiVo.getApiRoute());
                    boxAppApi.setRunClass(boxApiVo.getPackageClass());
                    boxAppApi.setRunFunction(boxApiVo.getClassFuntion());
                    boxAppApi.setLinkUrl(finalBoxApp.getRoute()+boxApiVo.getApiRoute());
                    boxAppApiMapper.add(boxAppApi);
                } else {
                    boxAppApi.setAppId(finalBoxApp.getAppId());
                    boxAppApi.setJarMd5(finalBoxApp.getJarMd5());
                    boxAppApi.setStats(0);
                    boxAppApi.setName(boxApiVo.getApiName());
                    boxAppApi.setRoute(boxApiVo.getApiRoute());
                    boxAppApi.setRunClass(boxApiVo.getPackageClass());
                    boxAppApi.setRunFunction(boxApiVo.getClassFuntion());
                    boxAppApi.setLinkUrl(finalBoxApp.getRoute()+boxApiVo.getApiRoute());
                    boxAppApiMapper.update(boxAppApi);
                }
            });
        });
       return false;
    }

    /**
     * 分页查询应用列表
     * @param appName
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<BoxAppVo> getList(String appName, int pageNo, int pageSize) throws Exception {

        // 组合查询语句
        Map<String,String> keys = new HashMap<>();
        keys.put(TabBoxApp.NAME, "like '%"+appName+"%'");
        pageNo = PageUtil.returnPageNo(pageNo,pageSize);
        keys.put("pageNo", pageNo+"");
        keys.put("pageSize", pageSize+"");

        // 查询数据
        List<BoxAppVo> boxAppVos = new ArrayList<>();
        boxAppMapper.getList(keys).forEach(boxApp -> {
            BoxAppVo boxAppVo = new BoxAppVo();
            BeanUtils.copyProperties(boxApp,boxAppVo);
            boxAppVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxApp.getCreateTime()));
            boxAppVos.add(boxAppVo);
        });
        return boxAppVos;
    }

    /**
     * 获取数据行数
     * @param appName
     * @return
     * @throws Exception
     */
    public int getListCount(String appName) throws Exception {

        // 组合查询语句
        Map<String,String> keys = new HashMap<>();
        keys.put(TabBoxApp.NAME, "like '%"+appName+"%'");
        return boxAppMapper.getListCount(keys);
    }
}
