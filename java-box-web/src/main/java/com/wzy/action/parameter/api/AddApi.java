package com.wzy.action.parameter.api;

import com.wzy.util.annotation.check.IsNotLong;
import com.wzy.util.annotation.check.IsNotNull;

public class AddApi {
    @IsNotNull(message = "请选择模块")
    @IsNotLong(message = "模块ID只能是数字")
    Integer moudularId;
    @IsNotNull(message = "Api名称不能未空")
    String apiName;
    @IsNotNull(message = "Api访问路径不能为空")
    String apiRoute;
    @IsNotNull(message = "Api包名类名不能为空")
    String classFuntion;
    String jsonp;

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public Integer getMoudularId() {
        return moudularId;
    }

    public void setMoudularId(Integer moudularId) {
        this.moudularId = moudularId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiRoute() {
        return apiRoute;
    }

    public void setApiRoute(String apiRoute) {
        this.apiRoute = apiRoute;
    }

    public String getClassFuntion() {
        return classFuntion;
    }

    public void setClassFuntion(String classFuntion) {
        this.classFuntion = classFuntion;
    }
}
