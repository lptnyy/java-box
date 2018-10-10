package com.wzy.server.jar.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzy.server.config.Config;
import com.wzy.util.http.HttpGetUtil;

import java.util.ArrayList;
import java.util.List;

public class NetApi {

    /**
     * 获取项目列表
     * @throws Exception
     */
    public static List<BoxProjectVo> getProjectList() throws Exception{
        List<BoxProjectVo> list = new ArrayList<>();
        String result = HttpGetUtil.get(Config.config.getServerMainPath()+Config.config.getGetProjectList(),"");
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

}
