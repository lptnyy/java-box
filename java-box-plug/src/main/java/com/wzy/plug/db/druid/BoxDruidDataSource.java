package com.wzy.plug.db.druid;
import com.alibaba.druid.pool.DruidDataSource;
import com.wzy.func.annotation.BoxConfigAdds;
import com.wzy.func.fc.IBoxDataSource;
import com.wzy.func.fc.IConfig;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import javax.sql.DataSource;
import java.util.Map;

// add config info zookeeper
@BoxConfigAdds(
        configKeys = {
                "druidurl",
                "druidusername",
                "druidpassword",
                "druiddriverClassName",
                "druidinitialSize",
                "druidminIdle",
                "druidmaxActive",
                "druidmaxWait",
                "druidtimeBetweenEvictionRunsMillis",
                "druidminEvictableIdleTimeMillis",
                "druidvalidationQuery",
                "druidtestWhileIdle",
                "druidtestOnBorrow",
                "druidtestOnReturn",
                "druidpoolPreparedStatements",
                "druidmaxPoolPreparedStatementPerConnectionSize",
                "druidconnectionProperties"
        },
        configValues = {
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""}
)
public class BoxDruidDataSource implements IBoxDataSource {
    ILog log = BoxLog.getInstance();
    DruidDataSource datasource;

    public DataSource dataSource() {
        return datasource;
    }

    @Override
    public Map<String, DataSource> dataSources() {
        return null;
    }

    // 初始化连接池
    @Override
    public void init(IConfig config) throws Exception {
        String url = config.getValue("druidurl");
        String username = config.getValue("druidusername");
        String password = config.getValue("druidpassword");
        String driverClassName = config.getValue("druiddriverClassName");
        int initialSize = Integer.valueOf(config.getValue("druidinitialSize"));
        int minIdle = Integer.valueOf(config.getValue("druidminIdle"));
        int maxActive = Integer.valueOf(config.getValue("druidmaxActive"));
        int maxWait = Integer.valueOf(config.getValue("druidmaxWait"));
        int timeBetweenEvictionRunsMillis = Integer.valueOf(config.getValue("druidtimeBetweenEvictionRunsMillis"));
        int minEvictableIdleTimeMillis = Integer.valueOf(config.getValue("druidminEvictableIdleTimeMillis"));
        String validationQuery = config.getValue("druidvalidationQuery");
        boolean testWhileIdle = Boolean.valueOf(config.getValue("druidtestWhileIdle"));
        boolean testOnBorrow = Boolean.valueOf(config.getValue("druidtestOnBorrow"));
        boolean testOnReturn = Boolean.valueOf(config.getValue("druidtestOnReturn"));
        boolean poolPreparedStatements = Boolean.valueOf(config.getValue("druidpoolPreparedStatements"));
        int maxPoolPreparedStatementPerConnectionSize = Integer.valueOf(config.getValue("druidmaxPoolPreparedStatementPerConnectionSize"));
        String connectionProperties = config.getValue("druidconnectionProperties");
        datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);   //这里可以做加密处理
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setConnectionProperties(connectionProperties);
        log.info("阿里云链接池 成功");
    }

    @Override
    public void close() {
        datasource.close();
    }
}
