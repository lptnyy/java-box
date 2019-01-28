package com.wzy.log;

public class BoxLog implements ILog {
    private static BoxLog boxLog = null;

    public static BoxLog getInstance(){
        if (boxLog == null) {
            synchronized (BoxLog.class){
                if (boxLog == null) {
                    boxLog = new BoxLog();
                }
            }
        }
        return boxLog;
    }

    @Override
    public void info(String info) {
        System.out.println(info);
    }

    @Override
    public void error(String info) {
        System.out.println(info);
    }

    @Override
    public void debug(String info) {
        System.out.println(info);
    }

    @Override
    public void warning(String info) {
        System.out.println(info);
    }

    public void error(Exception e)
    {
        e.printStackTrace();
    }

    @Override
    public void visit(String info) {
        System.out.println(info);
    }
}
