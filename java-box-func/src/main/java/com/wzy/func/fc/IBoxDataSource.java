package com.wzy.func.fc;

import javax.sql.DataSource;

public interface IBoxDataSource {

    public DataSource dataSource();

    public void init();
}
