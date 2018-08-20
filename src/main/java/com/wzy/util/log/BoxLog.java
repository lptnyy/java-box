package com.wzy.util.log;

public class BoxLog implements JavaBoxLog {

    @Override
    public void info(String info) {

    }

    @Override
    public void error(String info) {

    }

    @Override
    public void debug(String info) {

    }

    @Override
    public void warning(String info) {

    }

    public void error(Exception e)
    {
        e.printStackTrace();
    }
}
