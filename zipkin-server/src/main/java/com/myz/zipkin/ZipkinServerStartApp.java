package com.myz.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/5 11:46
 * @email 2641007740@qq.com
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerStartApp {

    public static void main(String[] args) {

        SpringApplication.run(ZipkinServerStartApp.class,args);
    }
}
