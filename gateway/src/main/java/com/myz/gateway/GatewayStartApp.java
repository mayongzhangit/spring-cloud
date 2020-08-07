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

        SpringApplication.run(GatewayStartApp.class,args);
    }

    public void run(String... args) throws Exception {
        log.info("gateway started successfully!");
    }
}
