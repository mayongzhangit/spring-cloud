package com.myz.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/31 16:27
 * @email 2641007740@qq.com
 */
@SpringBootApplication
public class DemoStartApp {

    public static void main(String[] args) {

        new SpringApplicationBuilder(DemoStartApp.class)
                .web(WebApplicationType.SERVLET)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }
}
