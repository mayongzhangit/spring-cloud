package com.myz.consumer.feign;

import com.myz.consumer.feign.fallback.factory.OrderFeignFallbackFactory;
import com.myz.order.api.OrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 19:20
 * @email 2641007740@qq.com
 */
@FeignClient(name = "sc-order-provider",url = "http://127.0.0.1:8083",fallbackFactory = OrderFeignFallbackFactory.class)
public interface OrderFeignApi extends OrderApi {
}
