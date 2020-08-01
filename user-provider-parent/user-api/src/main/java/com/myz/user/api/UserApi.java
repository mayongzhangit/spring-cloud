package com.myz.user.api;

import com.myz.user.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/28 20:14
 * @email 2641007740@qq.com
 */
public interface UserApi {

    String USER_PREFIX = "/user";

    @PostMapping(USER_PREFIX+"/save")
    Long saveUser(UserDto userDto);

    @GetMapping(USER_PREFIX+"/get/{id}")
    UserDto getById(@PathVariable("id") Long id) throws InterruptedException;
}
