package com.wzy.action.parameter.moudular;
import com.wzy.util.annotation.check.IsNotNull;

public class AddMoudular {
    @IsNotNull(message = "请选择项目")
    Integer projectId;
    @IsNotNull(message = "输入模块跟目录")
    String moddularRoute;
    @IsNotNull(message = "输入模块名称")
    String moddularName;
    @IsNotNull(message = "jar名称不能为空")
    String jarName;
    @IsNotNull(message = "jar上传地址不能空")
    String jarUrl;
    @IsNotNull(message = "jar版本号不能为空")
    String jarVersion;
    @IsNotNull(message = "md5文件不能为空")
    String jarMd5;
    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public String getJarMd5() {
        return jarMd5;
    }

    public void setJarMd5(String jarMd5) {
        this.jarMd5 = jarMd5;
    }

    String jsonp;
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getModdularName() {
        return moddularName;
    }

    public void setModdularName(String moddularName) {
        this.moddularName = moddularName;
    }

    public String getModdularRoute() {
        return moddularRoute;
    }

    public void setModdularRoute(String moddularRoute) {
        this.moddularRoute = moddularRoute;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getJarUrl() {
        return jarUrl;
    }

    public void setJarUrl(String jarUrl) {
        this.jarUrl = jarUrl;
    }

    public String getJarVersion() {
        return jarVersion;
    }

    public void setJarVersion(String jarVersion) {
        this.jarVersion = jarVersion;
    }
}
