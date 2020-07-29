package com.myz.user.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/28 20:37
 * @email 2641007740@qq.com
 */
//@SpringCloudApplication
@Slf4j
@SpringBootApplication
public class UserStartApp implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(UserStartApp.class,args);
    }

    public void run(String... args) throws Exception {
        log.info("start success args={}",args);
    }
}
