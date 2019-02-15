package com.wzy.func.fc;

import org.apache.zookeeper.KeeperException;

import java.util.List;

public interface IConfig {
    List<String> keys();
    List<String> values();
    String getValue(String value) throws KeeperException, InterruptedException;
    IConfig addView(String key,String value);
}
