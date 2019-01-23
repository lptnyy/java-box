package com.wzy.util.http;

public class UrlPart {

    // 获取地址是否与拦截地址匹配
    public static boolean path(String watchStr, String url) {
        try {
            String[] watchStrs = watchStr.split("/");
            String[] urls = url.split("/");
            boolean[] watchBoolen = new boolean[watchStrs.length];
            for (int k=0;k<watchStrs.length;k++) {
                String watchStrObj= watchStrs[k];
                if (watchStrObj.equals("*")) {
                    watchBoolen[k] = true;
                    for (int i = k; i<watchStrs.length; i++) {
                        watchBoolen[i] = true;
                    }
                    break;
                }
                if (k < urls.length) {
                   String urlsObj=urls[k];
                   if (watchStrObj.equals(urlsObj)){
                       watchBoolen[k] = true;
                   } else {
                       watchBoolen[k] = false;
                   }
                } else {
                    watchBoolen[k] = false;
                }
            }
            for(boolean bl:watchBoolen) {
                if (bl == false) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {

        }
       return false;
    }
}
