package com.wzy.action;

import com.wzy.action.parameter.project.IdProject;
import com.wzy.action.parameter.project.ListProject;
import com.wzy.action.parameter.project.Project;
import com.wzy.action.parameter.project.UpdateOpenStat;
import com.wzy.entity.vo.BoxProjectVo;
import com.wzy.util.MapUtil;
import com.wzy.util.base.BaseServiceI;
import com.wzy.service.ProjectServiceI;
import com.wzy.util.annotation.factory.Verification;
import com.wzy.util.exception.MyExceptionUtil;
import com.wzy.util.jsonvo.JsonVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class BoxProjectController {

    @Autowired
    BaseServiceI<BoxProjectVo> service;
    @Autowired
    ProjectServiceI projectServiceI;

    /**
     * 添加项目
     *
     * @return
     */
    @RequestMapping(value = "/addProject")
    public String addProject(Project project) {
        return Verification.verification(project)
                .setJsonp(project.getJsonp())
                .setBusiness(jsonVo -> {
                    BoxProjectVo projectVo = new BoxProjectVo();
                    BeanUtils.copyProperties(project, projectVo);
                    try {
                        service.add(projectVo);
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(), false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 修改项目
     *
     * @return
     */
    @RequestMapping(value = "/delProject")
    public String delProject(IdProject project) {
        return Verification.verification(project)
                .setJsonp(project.getJsonp())
                .setBusiness(jsonVo -> {
                    try {
                        service.del(project.getId());
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 修改项目
     *
     * @return
     */
    @RequestMapping(value = "/getProject")
    public String getProject(IdProject project) {
        return Verification.verification(project)
                .setJsonp(project.getJsonp())
                .setBusiness(jsonVo -> {
                    try {
                        jsonVo.setObject(service.get(project.getId()));
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 修改项目
     *
     * @return
     */
    @RequestMapping(value = "/getProjectList")
    public String getProjectList(ListProject project) {
        return Verification.verification(project)
                .setJsonp(project.getJsonp())
                .setBusiness(jsonVo -> {
                    try {
                        Map map = MapUtil.objectToMap(project);
                        jsonVo.setObject(service.getFindList(map,project.getPageNo(), project.getPageSize()));
                        jsonVo.setSumPage(service.getFindListCount(map));
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 修改发布状态
     * @param updateOpenStat
     * @return
     */
    @RequestMapping(value = "/updateProjectOpenStat")
    public String updateProjectOpenStat(UpdateOpenStat updateOpenStat) {
        return Verification.verification(updateOpenStat)
                .setJsonp(updateOpenStat.getJsonp())
                .setBusiness(jsonVo -> {
                    try{
                        projectServiceI.updateOpenStat(updateOpenStat.getId(),updateOpenStat.getOpenStat());
                    }catch (Exception e){
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }

    /**
     * 下拉菜单获取
     *
     * @return
     */
    @RequestMapping(value = "/getOptionProjectList")
    public String getOptionProjectList(ListProject project) {
        return new JsonVo().setResult(true)
                .setJsonp(project.getJsonp())
                .setBusiness(jsonVo -> {
                    try {
                        jsonVo.setObject(service.getFindList(null,1, Integer.MAX_VALUE));
                    } catch (Exception e) {
                        MyExceptionUtil.error(e);
                        jsonVo.setBody(e.getMessage(),false);
                    }
                    return jsonVo;
                }).init().returnJsonString();
    }
}
