package com.myz.consumer.feign.fallback.factory;

import com.myz.consumer.feign.fallback.OrderFeignFallbackApi;
import com.myz.order.api.OrderApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 19:22
 * @email 2641007740@qq.com
 */
@Component
public class OrderFeignFallbackFactory implements FallbackFactory<OrderApi> {

    public OrderApi create(Throwable cause) {
        return new OrderFeignFallbackApi();
    }
}
