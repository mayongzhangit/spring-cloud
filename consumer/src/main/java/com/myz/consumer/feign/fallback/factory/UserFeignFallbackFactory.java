package com.myz.consumer.feign.fallback.factory;

import com.myz.consumer.feign.UserFeignApi;
import com.myz.consumer.feign.fallback.UserFeignFallbackApi;
import com.myz.user.api.UserApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 16:55
 * @email 2641007740@qq.com
 */
@Slf4j
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserApi> {
    public UserApi create(Throwable cause) {
        return new UserFeignFallbackApi();
    }
}
