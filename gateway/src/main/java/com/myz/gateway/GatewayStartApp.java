package com.myz.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 18:26
 * @email 2641007740@qq.com
 */
@Slf4j
@SpringBootApplication
public class GatewayStartApp implements CommandLineRunner {

    public static void main(String[] args) {

        System.setProperty("reactor.netty.http.server.accessLogEnabled","true");
        SpringApplication.run(GatewayStartApp.class,args);
    }

    public void run(String... args) throws Exception {
        log.info("\n*********gateway config*********\n" +
                "1) Reactor Netty Access Log (https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.5.RELEASE/single/spring-cloud-gateway.html#_reactor_netty_access_logs)\n" +
                "2) Hystrix fallback forword set GatewayHystrixFallbackController\n" +
                "3)\n"
        );
    }
}
