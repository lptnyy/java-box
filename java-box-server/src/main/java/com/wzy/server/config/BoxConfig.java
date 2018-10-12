package com.wzy.server.config;

public class BoxConfig {
    String serverMainPath;
    String jarDownServerUrl;
    String regionServerIp;
    String getApplist;
    String getAppApiList;
    String getAppInfo;
    String charSet;
    int regsionServerPort;
    int regsionServerTimeOut;
    boolean openAdmin;
    int adminPort;
    int ServerPort;

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getGetApplist() {
        return getApplist;
    }

    public void setGetApplist(String getApplist) {
        this.getApplist = getApplist;
    }

    public String getGetAppApiList() {
        return getAppApiList;
    }

    public String getGetAppInfo() {
        return getAppInfo;
    }

    public void setGetAppInfo(String getAppInfo) {
        this.getAppInfo = getAppInfo;
    }

    public void setGetAppApiList(String getAppApiList) {
        this.getAppApiList = getAppApiList;
    }

    public boolean isOpenAdmin() {
        return openAdmin;
    }

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

    public int getAdminPort() {
        return adminPort;
    }

    public void setAdminPort(int adminPort) {
        this.adminPort = adminPort;
    }

    public int getServerPort() {
        return ServerPort;
    }

    public void setServerPort(int serverPort) {
        ServerPort = serverPort;
    }

    public boolean getOpenAdmin() {
        return openAdmin;
    }

    public void setOpenAdmin(boolean openAdmin) {
        this.openAdmin = openAdmin;
    }
}
