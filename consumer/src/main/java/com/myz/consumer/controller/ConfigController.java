package com.myz.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzMa
 * 测试步骤：
 *  1）curl http://localhost:8079/config/get-data
 *  2）http://localhost:8079/change-config/reset?key=my.data&newVal=aaaaaa  // 模拟ConfigServer中变量值变更
 *  3）curl -XPOST -H "Content-type:application/json;" http://localhost:8079/actuator/refresh
 *  4）curl http://localhost:8079/config/get-data // 再次请求进行检查
 * @desc
 * @date 2020/8/4 14:48
 * @email 2641007740@qq.com
 */
@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${my.data:default}")
    private String data;

    @GetMapping("/get-data")
    public String getData(){
        return data;
    }
}
