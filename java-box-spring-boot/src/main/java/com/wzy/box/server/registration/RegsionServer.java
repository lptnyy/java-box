package com.wzy.box.server.registration;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import com.wzy.server.config.Config;
import com.wzy.server.region.RegionServer;
import com.wzy.server.region.ServerNode;
import com.wzy.server.region.zookeeper.ZkNetConfig;
import com.wzy.server.region.zookeeper.ZkServer;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
public class RegsionServer implements RegionServer {
    @Value("${javabox.zookeeper_ip}")
    String zookeeper_ip;
    @Value("${javabox.zookeeper_port}")
    String zookeeper_port;
    @Value("${javabox.zookeeper_time_out}")
    String zookeeper_time_out;
    @Value("${javabox.server_ip}")
    String server_ip;
    @Value("${javabox.server_port}")
    String server_port;

    static ILog log = BoxLog.getInstance();
    public static ZkNetConfig zkNetConfig = null;

    @Override
    public void regionService() {
        // 初始化 配置文件
        if (Config.initSpringBootEnvConfig(zookeeper_ip,zookeeper_port,zookeeper_time_out,server_ip,server_port)) {
            ServerNode node = new ServerNode();
            node.serverType = 1;
            ZkServer zkServer = new ZkServer();
            zkServer.regionService();
            while (zkServer.isConnect()) {
                log.info("load zk");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            zkNetConfig = new ZkNetConfig();
            zkNetConfig.setZkServer(zkServer);
            Config.iConfig = zkNetConfig;
            try {
                Config.initZkConfig(zkNetConfig);
                zkServer.initNode(node);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.error("启动服务失败");
        }
    }
}
