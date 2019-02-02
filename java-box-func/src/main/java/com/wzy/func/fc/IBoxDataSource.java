package com.wzy.func.fc;

import org.apache.zookeeper.KeeperException;

import javax.sql.DataSource;

public interface IBoxDataSource {

    public DataSource dataSource();

    public void init(IConfig config) throws Exception;
}
