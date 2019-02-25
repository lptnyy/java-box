package com.wzy.box.server;
import com.wzy.box.server.util.BoxApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerStart {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context =SpringApplication.run(ServerStart.class, args);
        BoxApplicationContext.setApplicationContext(context);
    }
}
