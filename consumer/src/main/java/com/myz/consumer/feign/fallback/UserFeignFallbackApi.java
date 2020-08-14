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

    public String stressTest(Long cost, boolean exFlag) {
        return "stress test fallback";
    }

    public Long saveUser(UserDto userDto) {

        return -1L;
    }

    public UserDto getById(Long id) {
        UserDto userDto = new UserDto();
        userDto.setId(-1L);
        return userDto;
    }
}
