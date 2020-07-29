package com.myz.consumer.controller;

import com.myz.consumer.controller.vo.IndexVo;
import com.myz.consumer.feign.UserFeignApi;
import com.myz.user.api.UserApi;
import com.myz.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 16:38
 * @email 2641007740@qq.com
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserFeignApi userFeignApi;

    @GetMapping("/index")
    public IndexVo index(){
        Long id = 1l;
        UserDto userDto = userFeignApi.getById(id);

        IndexVo indexVo = new IndexVo();
        indexVo.setUserDto(userDto);
        return indexVo;
    }
}
