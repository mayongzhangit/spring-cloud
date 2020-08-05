package com.myz.discovery.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/5 14:06
 * @email 2641007740@qq.com
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryStartApp {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryStartApp.class,args);
    }
}
