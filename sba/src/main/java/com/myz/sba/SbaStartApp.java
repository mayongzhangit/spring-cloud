package com.myz.sba;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/11 16:35
 * @email 2641007740@qq.com
 */
@EnableAdminServer
@SpringBootApplication
public class SbaStartApp {

    public static void main(String[] args) {

        SpringApplication.run(SbaStartApp.class,args);
    }
}
