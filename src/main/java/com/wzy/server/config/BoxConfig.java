package com.wzy.server.config;

public class BoxConfig {
    String serverMainPath;
    String getProjectList;
    String getMouderList;
    String getApiList;
    String jarDownServerUrl;
    String regionServerIp;
    int regsionServerPort;
    int regsionServerTimeOut;
    int port;

    public int getRegsionServerTimeOut() {
        return regsionServerTimeOut;
    }

    public void setRegsionServerTimeOut(int regsionServerTimeOut) {
        this.regsionServerTimeOut = regsionServerTimeOut;
    }

    public String getRegionServerIp() {
        return regionServerIp;
    }

    public void setRegionServerIp(String regionServerIp) {
        this.regionServerIp = regionServerIp;
    }

    public int getRegsionServerPort() {
        return regsionServerPort;
    }

    public void setRegsionServerPort(int regsionServerPort) {
        this.regsionServerPort = regsionServerPort;
    }

    public String getJarDownServerUrl() {
        return jarDownServerUrl;
    }

    public void setJarDownServerUrl(String jarDownServerUrl) {
        this.jarDownServerUrl = jarDownServerUrl;
    }

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
