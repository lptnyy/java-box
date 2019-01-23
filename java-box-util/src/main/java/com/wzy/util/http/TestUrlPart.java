package com.wzy.util.http;

public class TestUrlPart {
    public static void main(String[] args) {
        System.out.println(UrlPart.path("/home/*","/home/index"));
    }
}
