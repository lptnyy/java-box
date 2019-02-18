package com.wzy.func.fc;

import org.apache.zookeeper.KeeperException;

import javax.sql.DataSource;
import java.util.Map;

public interface IBoxDataSource {
    // 获取连接池
    public DataSource dataSource();

    // 多数据源配置
    public Map<String,DataSource> dataSources();
}
