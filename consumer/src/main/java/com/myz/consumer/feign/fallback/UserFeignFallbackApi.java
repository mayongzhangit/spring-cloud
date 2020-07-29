package com.myz.consumer.feign.fallback;

import com.myz.user.api.UserApi;
import com.myz.user.dto.UserDto;
import org.springframework.stereotype.Component;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 16:54
 * @email 2641007740@qq.com
 */
//@Component
public class UserFeignFallbackApi implements UserApi {

    public Long saveUser(UserDto userDto) {
        return null;
    }

    public UserDto getById(Long id) {
        return null;
    }
}
