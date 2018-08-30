package com.wzy.action.parameter.project;

import com.wzy.util.annotation.check.IsNotLong;
import com.wzy.util.annotation.check.IsNotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Project {
    @IsNotNull(message = "项目访问路径不能为空", soft = 2)
    String Route;
    @IsNotNull(message = "项目名称不能为空",soft = 1)
    String projectName;
    String jsonp;
    public String getJsonp() {
        return jsonp;
    }
    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }
}
