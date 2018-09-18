package com.wzy.action.parameter.api;

import com.wzy.util.annotation.check.IsNotLong;
import com.wzy.util.annotation.check.IsNotNull;

public class GetApiLIst {
    @IsNotNull(message = "pageNo不能为空")
    @IsNotLong(message = "pageNo只能是数字")
    Integer pageNo;
    @IsNotNull(message = "pageSize不能为空")
    @IsNotLong(message = "pageSize只能是数字")
    Integer pageSize;
    Integer moudularId;

    public Integer getMoudularId() {
        return moudularId;
    }
    public void setMoudularId(Integer moudularId) {
        this.moudularId = moudularId;
    }
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    String jsonp;
}
