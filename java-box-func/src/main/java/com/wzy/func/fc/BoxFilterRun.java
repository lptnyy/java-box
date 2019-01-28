package com.wzy.func.fc;

public class BoxFilterRun {
    // 过滤器返回失败
    public static int RUNERROR=1;
    // 没有过滤器可用
    public static int RUNNULL=2;
    // 过滤器执行成功
    public static int RUNSU=0;

    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
