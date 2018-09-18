package com.wzy.util.exception;

public class MyExceptionUtil {
    public static String INTERERROR = "服务器错误";

    public static void error(String msg) {
        System.out.println(msg);
    }

    public static void info(String msg) {
        System.out.println(msg);
    }

    public static void warn(String msg) {
        System.out.println(msg);
    }

    public static void debug(String msg){
        System.out.println(msg);
    }

    public static void error(Exception e){error(e.getMessage());}
}
