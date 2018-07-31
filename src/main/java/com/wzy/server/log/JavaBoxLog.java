package com.wzy.server.log;

public interface JavaBoxLog {
    void info(String info);
    void error(String info);
    void debug(String info);
    void warning(String info);
    void error(Exception e);
}
