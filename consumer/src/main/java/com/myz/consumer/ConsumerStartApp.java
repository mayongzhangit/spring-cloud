package com.myz.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 16:19
 * @email 2641007740@qq.com
 */
@EnableFeignClients(basePackages = {"com.myz.consumer.feign"})
@SpringBootApplication
public class ConsumerStartApp {

    public static void main(String[] args) {

        SpringApplication.run(ConsumerStartApp.class,args);
    }

}
