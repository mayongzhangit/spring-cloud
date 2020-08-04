package com.myz.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/4 14:39
 * @email 2641007740@qq.com
 */
@Slf4j
@RestController
@RequestMapping("/change-config")
public class ChangeConfigController {

    @Autowired
    private ConfigurableEnvironment environment;

    @GetMapping("/reset")
    public void resetData2Environment(@RequestParam String key,@RequestParam String newVal){

        Map<String, Object> systemProperties = environment.getSystemProperties();
        systemProperties.put(key,newVal);// SystemProperties排在 application.yml之前
    }
}
