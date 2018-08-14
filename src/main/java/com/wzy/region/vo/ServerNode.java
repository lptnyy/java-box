package com.wzy.region.vo;
public class ServerNode {
    public String javaVersion;         // Java 运行时环境版本
    public String javaVendor;         //Java 运行时环境供应商
    public String javaVendorUrl;        // Java 供应商的 URL
    public String javaVmSpecificationVersion;         //Java 虚拟机规范版本
    public String javaVmSpecificationVendor;         //Java 虚拟机规范供应商
    public String javaVmSpecificationName;         //Java 虚拟机规范名称
    public String javaVmVersion;         //Java 虚拟机实现版本
    public String javaVmVendor;         //Java 虚拟机实现供应商
    public String javaVmName;         //Java 虚拟机实现名称
    public String javaSpecificationVersion;         //Java 运行时环境规范版本
    public String javaSpecificationVendor;         //Java 运行时环境规范供应商
    public String javaSpecificationName;         //Java 运行时环境规范名称
    public String osName;         //操作系统的名称
    public String osArch;         //操作系统的架构
    public String osVersion;        // 操作系统的版本
    public String fileSeparator;        // 文件分隔符（在 UNIX 系统中是“ / ”）
    public String pathSeparator;        // 路径分隔符（在 UNIX 系统中是“ : ”）
    public String lineSeparator;        // 行分隔符（在 UNIX 系统中是“ /n ”）
    public String javaHome;        // Java 安装目录
    public String javaClassVersion;        // Java 类格式版本号
    public String javaClassPath;        // Java 类路径
    public String javaLibraryPath;        //  加载库时搜索的路径列表
    public String javaIoTmpdir;        // 默认的临时文件路径
    public String javaCompiler;        // 要使用的 JIT 编译器的名称
    public String javaExtDirs;        // 一个或多个扩展目录的路径
    public String userName;        // 用户的账户名称
    public String userHome;        // 用户的主目录
    public String userDir;  //当前程序的物理路径
    public Integer port;    //服务绑定的端口
    public String ip; // 服务端绑定的Ip
}
