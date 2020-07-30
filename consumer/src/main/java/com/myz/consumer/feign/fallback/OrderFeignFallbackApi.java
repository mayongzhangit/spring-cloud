package com.myz.consumer.feign.fallback;

import com.myz.order.api.OrderApi;
import com.myz.order.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 19:27
 * @email 2641007740@qq.com
 */
public class OrderFeignFallbackApi implements OrderApi {
    public Long generate(Long goodsId) {
        return -1L;
    }

    public List<OrderDto> listByUserId(Long userId) {
        return new ArrayList<OrderDto>();
    }
}
