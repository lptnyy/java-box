package com.wzy.config;

public class BoxConfig {
    String serverMainPath;
    String getProjectList;
    String getMouderList;
    String getApiList;
    String jarDownServerUrl;

    public String getJarDownServerUrl() {
        return jarDownServerUrl;
    }

    public void setJarDownServerUrl(String jarDownServerUrl) {
        this.jarDownServerUrl = jarDownServerUrl;
    }

    int port;

    public String getServerMainPath() {
        return serverMainPath;
    }

    public void setServerMainPath(String serverMainPath) {
        this.serverMainPath = serverMainPath;
    }

    public String getGetProjectList() {
        return getProjectList;
    }

    public void setGetProjectList(String getProjectList) {
        this.getProjectList = getProjectList;
    }

    public String getGetMouderList() {
        return getMouderList;
    }

    public void setGetMouderList(String getMouderList) {
        this.getMouderList = getMouderList;
    }

    public String getGetApiList() {
        return getApiList;
    }

    public void setGetApiList(String getApiList) {
        this.getApiList = getApiList;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
