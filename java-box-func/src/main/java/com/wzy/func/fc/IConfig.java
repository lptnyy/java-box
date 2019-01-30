package com.wzy.func.fc;

import org.apache.zookeeper.KeeperException;

import java.util.List;

public interface IConfig {
    List<String> keys();
    String getValue(String value) throws KeeperException, InterruptedException;
}
