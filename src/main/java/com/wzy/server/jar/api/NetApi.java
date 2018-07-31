package com.wzy.server.jar.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzy.server.jar.api.vo.BoxApiVo;
import com.wzy.server.jar.api.vo.BoxMoudulaVo;
import com.wzy.server.jar.api.vo.BoxProjectVo;
import com.wzy.server.config.Config;
import com.wzy.util.http.HttpGet;

import java.util.ArrayList;
import java.util.List;

public class NetApi {

    /**
     * 获取项目列表
     * @throws Exception
     */
    public static List<BoxProjectVo> getProjectList() throws Exception{
        List<BoxProjectVo> list = new ArrayList<>();
        String result = HttpGet.get(Config.config.getServerMainPath()+Config.config.getGetProjectList(),"");
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getBoolean("result")) {
            JSONArray jsonArray = jsonObject.getJSONArray("object");
            for(int i = 0; i< jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Integer projectId = obj.getInteger("projectId");
                String projectName = obj.getString("projectName");
                String Route = obj.getString("route");
                Integer openStat = obj.getInteger("openStat");
                String createTime = obj.getString("createTime");
                BoxProjectVo projectVo = new BoxProjectVo();
                projectVo.setCreateTime(createTime);
                projectVo.setOpenStat(openStat);
                projectVo.setProjectId(projectId);
                projectVo.setProjectName(projectName);
                projectVo.setRoute(Route);
                list.add(projectVo);
            }
        }
        return list;
    }

    /**
     * 查询项目模块
     * @param projectId
     * @return
     */
    public static List<BoxMoudulaVo> getNoudulaVo(String projectId) throws Exception{
      List<BoxMoudulaVo> list = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("projectId=").append(projectId);
        String result = HttpGet.get(Config.config.getServerMainPath()+Config.config.getGetMouderList(),stringBuffer.toString());
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getBoolean("result")) {
            JSONArray jsonArray = jsonObject.getJSONArray("object");
            for(int i = 0; i< jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                BoxMoudulaVo boxMoudulaVo = new BoxMoudulaVo();
                Integer moudularId = obj.getInteger("moudularId");
                Integer projectIds = obj.getInteger("projectId");
                String moddularName = obj.getString("moddularName");
                String moddularRoute = obj.getString("moddularRoute");
                String jarName = obj.getString("jarName");
                String jarUrl = obj.getString("jarUrl");
                String jarVersion = obj.getString("jarVersion");
                Integer isUpdate = obj.getInteger("isUpdate");
                boxMoudulaVo.setMoudularId(moudularId);
                boxMoudulaVo.setProjectId(projectIds);
                boxMoudulaVo.setModdularName(moddularName);
                boxMoudulaVo.setModdularRoute(moddularRoute);
                boxMoudulaVo.setJarName(jarName);
                boxMoudulaVo.setJarUrl(jarUrl);
                boxMoudulaVo.setJarVersion(jarVersion);
                boxMoudulaVo.setIsUpdate(isUpdate);
                list.add(boxMoudulaVo);
            }
        }
        return list;
    }

    /**
     * 查询Api接口列表
     * @param moudularId
     * @return
     * @throws Exception
     */
    public static List<BoxApiVo> getApiVo(String moudularId) throws Exception{
      List<BoxApiVo> list = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("moudularId=").append(moudularId);
        String result = HttpGet.get(Config.config.getServerMainPath()+Config.config.getGetApiList(),stringBuffer.toString());
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getBoolean("result")) {
            JSONArray jsonArray = jsonObject.getJSONArray("object");
            for(int i = 0; i< jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                BoxApiVo apiVo = new BoxApiVo();
                Integer apiId = obj.getInteger("apiId");
                Integer moudularIds = obj.getInteger("moudularId");
                String apiName = obj.getString("apiName");
                String apiRoute = obj.getString("apiRoute");
                String classFuntion = obj.getString("classFuntion");
                Integer sendStat = obj.getInteger("sendStat");
                String createTime = obj.getString("createTime");
                apiVo.setApiId(apiId);
                apiVo.setMoudularId(moudularIds);
                apiVo.setApiName(apiName);
                apiVo.setApiRoute(apiRoute);
                apiVo.setClassFuntion(classFuntion);
                apiVo.setSendStat(sendStat);
                apiVo.setCreateTime(createTime);
                list.add(apiVo);
            }
        }
        return list;
    }
}
