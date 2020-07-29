package com.myz.user.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/28 20:58
 * @email 2641007740@qq.com
 */
@RestController
public class OkController {

    @GetMapping("/ok")
    public String ok(){
        return "ok";
    }
}
