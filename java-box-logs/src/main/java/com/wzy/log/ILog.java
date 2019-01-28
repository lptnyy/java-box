package com.wzy.log;

public interface ILog {
    void info(String info);
    void error(String info);
    void debug(String info);
    void warning(String info);
    void error(Exception e);
    void visit(String info);
}
