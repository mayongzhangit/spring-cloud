package com.myz.consumer.feign;

import com.myz.consumer.feign.fallback.factory.UserFeignFallbackFactory;
import com.myz.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yzMa
 * @desc
 *  详情：url比name优先 FeignClientFactoryBean#getTarget()
 * @date 2020/7/29 16:52
 * @email 2641007740@qq.com
 */
@FeignClient(name = "sc-user-provider",url = "http://127.0.0.1:8081",fallbackFactory = UserFeignFallbackFactory.class)
public interface UserFeignApi extends UserApi {
}
