package com.linck.management;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author linck
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.linck.management.**.mapper")
public class ManagementApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(ManagementApplication.class, args);
        ConfigurableEnvironment environment = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String path = environment.getProperty("server.servlet.context-path");
        log.info("""
                        项目启动成功
                        Local: \t\t\thttp://localhost:{}{}
                        External: \t\thttp://{}:{}{}
                        Swagger: \t\thttp://localhost:{}{}/doc.html
                        """
                , port, path, ip, port, path, port, path
        );
    }
}
