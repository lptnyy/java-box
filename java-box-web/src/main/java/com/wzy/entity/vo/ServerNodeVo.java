package com.wzy.entity.vo;

public class ServerNodeVo {
    String javaVersion;         // Java 运行时环境版本
    String javaVendor;         //Java 运行时环境供应商
    String javaVendorUrl;        // Java 供应商的 URL
    String javaVmSpecificationVersion;         //Java 虚拟机规范版本
    String javaVmSpecificationVendor;         //Java 虚拟机规范供应商
    String javaVmSpecificationName;         //Java 虚拟机规范名称
    String javaVmVersion;         //Java 虚拟机实现版本
    String javaVmVendor;         //Java 虚拟机实现供应商
    String javaVmName;         //Java 虚拟机实现名称
    String javaSpecificationVersion;         //Java 运行时环境规范版本
    String javaSpecificationVendor;         //Java 运行时环境规范供应商
    String javaSpecificationName;         //Java 运行时环境规范名称
    String osName;         //操作系统的名称
    String osArch;         //操作系统的架构
    String osVersion;        // 操作系统的版本
    String fileSeparator;        // 文件分隔符（在 UNIX 系统中是“ / ”）
    String pathSeparator;        // 路径分隔符（在 UNIX 系统中是“ : ”）
    String lineSeparator;        // 行分隔符（在 UNIX 系统中是“ /n ”）
    String javaHome;        // Java 安装目录
    String javaClassVersion;        // Java 类格式版本号
    String javaClassPath;        // Java 类路径
    String javaLibraryPath;        //  加载库时搜索的路径列表
    String javaIoTmpdir;        // 默认的临时文件路径
    String javaCompiler;        // 要使用的 JIT 编译器的名称
    String javaExtDirs;        // 一个或多个扩展目录的路径
    String userName;        // 用户的账户名称
    String userHome;        // 用户的主目录
    String userDir;  //当前程序的物理路径
    Integer port;    //服务绑定的端口
    String ip; // 服务端绑定的Ip

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getJavaVendor() {
        return javaVendor;
    }

    public void setJavaVendor(String javaVendor) {
        this.javaVendor = javaVendor;
    }

    public String getJavaVendorUrl() {
        return javaVendorUrl;
    }

    public void setJavaVendorUrl(String javaVendorUrl) {
        this.javaVendorUrl = javaVendorUrl;
    }

    public String getJavaVmSpecificationVersion() {
        return javaVmSpecificationVersion;
    }

    public void setJavaVmSpecificationVersion(String javaVmSpecificationVersion) {
        this.javaVmSpecificationVersion = javaVmSpecificationVersion;
    }

    public String getJavaVmSpecificationVendor() {
        return javaVmSpecificationVendor;
    }

    public void setJavaVmSpecificationVendor(String javaVmSpecificationVendor) {
        this.javaVmSpecificationVendor = javaVmSpecificationVendor;
    }

    public String getJavaVmSpecificationName() {
        return javaVmSpecificationName;
    }

    public void setJavaVmSpecificationName(String javaVmSpecificationName) {
        this.javaVmSpecificationName = javaVmSpecificationName;
    }

    public String getJavaVmVersion() {
        return javaVmVersion;
    }

    public void setJavaVmVersion(String javaVmVersion) {
        this.javaVmVersion = javaVmVersion;
    }

    public String getJavaVmVendor() {
        return javaVmVendor;
    }

    public void setJavaVmVendor(String javaVmVendor) {
        this.javaVmVendor = javaVmVendor;
    }

    public String getJavaVmName() {
        return javaVmName;
    }

    public void setJavaVmName(String javaVmName) {
        this.javaVmName = javaVmName;
    }

    public String getJavaSpecificationVersion() {
        return javaSpecificationVersion;
    }

    public void setJavaSpecificationVersion(String javaSpecificationVersion) {
        this.javaSpecificationVersion = javaSpecificationVersion;
    }

    public String getJavaSpecificationVendor() {
        return javaSpecificationVendor;
    }

    public void setJavaSpecificationVendor(String javaSpecificationVendor) {
        this.javaSpecificationVendor = javaSpecificationVendor;
    }

    public String getJavaSpecificationName() {
        return javaSpecificationName;
    }

    public void setJavaSpecificationName(String javaSpecificationName) {
        this.javaSpecificationName = javaSpecificationName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getFileSeparator() {
        return fileSeparator;
    }

    public void setFileSeparator(String fileSeparator) {
        this.fileSeparator = fileSeparator;
    }

    public String getPathSeparator() {
        return pathSeparator;
    }

    public void setPathSeparator(String pathSeparator) {
        this.pathSeparator = pathSeparator;
    }

    public String getLineSeparator() {
        return lineSeparator;
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getJavaClassVersion() {
        return javaClassVersion;
    }

    public void setJavaClassVersion(String javaClassVersion) {
        this.javaClassVersion = javaClassVersion;
    }

    public String getJavaClassPath() {
        return javaClassPath;
    }

    public void setJavaClassPath(String javaClassPath) {
        this.javaClassPath = javaClassPath;
    }

    public String getJavaLibraryPath() {
        return javaLibraryPath;
    }

    public void setJavaLibraryPath(String javaLibraryPath) {
        this.javaLibraryPath = javaLibraryPath;
    }

    public String getJavaIoTmpdir() {
        return javaIoTmpdir;
    }

    public void setJavaIoTmpdir(String javaIoTmpdir) {
        this.javaIoTmpdir = javaIoTmpdir;
    }

    public String getJavaCompiler() {
        return javaCompiler;
    }

    public void setJavaCompiler(String javaCompiler) {
        this.javaCompiler = javaCompiler;
    }

    public String getJavaExtDirs() {
        return javaExtDirs;
    }

    public void setJavaExtDirs(String javaExtDirs) {
        this.javaExtDirs = javaExtDirs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHome() {
        return userHome;
    }

    public void setUserHome(String userHome) {
        this.userHome = userHome;
    }

    public String getUserDir() {
        return userDir;
    }

    public void setUserDir(String userDir) {
        this.userDir = userDir;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
