package com.wzy.util.jsonvo;

import com.wzy.util.AESOperator;
import com.wzy.util.exception.MyExceptionUtil;

public class JsonVo {
    boolean jsonp = false;
    Object object;
    Integer sumPage;

    public Integer getSumPage() {
        return sumPage;
    }

    public void setSumPage(Integer sumPage) {
        this.sumPage = sumPage;
    }

    public void setCallback(JsonVoInterface callback) {
        this.callback = callback;
    }

    boolean result = false;
    String msg;

    public JsonVo setJsonp(String jsonp) {
        if (jsonp!= null && jsonp.equals("true"))
            this.jsonp = true;
        return this;
    }


    JsonVoInterface callback;

    public Object getObject() {
        return object;
    }

    public JsonVo setObject(Object object) {
        this.object = object;
        return this;
    }

    public boolean isResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public JsonVo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public JsonVo setBusiness(JsonVoInterface callback){
        this.callback = callback;
        return this;
    }

    public JsonVo init(){
        if (result) {
            try{
                callback.jsonVoCallBack(this);
            }catch (Exception e){
                this.setResult(false);
                this.setMsg("服务器异常");
                MyExceptionUtil.error(e);
            }
        }
        return this;
    }

    public JsonVo setBody(String msg, boolean result){
        this.msg = msg;
        this.result = result;
        return this;
    }

    public String returnJsonString() {
        String JsonStr = AESOperator.returnStr(this);
        if (jsonp){
            JsonStr = "callback("+JsonStr+")";
        }
        return JsonStr;
    }

    public JsonVo setResult(boolean result) {
        this.result = result;
        return this;
    }
}
