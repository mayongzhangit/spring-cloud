package com.myz.order.api;

import com.myz.order.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 18:30
 * @email 2641007740@qq.com
 */
public interface OrderApi {

    String ORDER_PREFIX = "/order";

    @PostMapping(ORDER_PREFIX+"/generate/{goodsId}}")
    Long generate(@PathVariable("goodsId") Long goodsId);

    @GetMapping(ORDER_PREFIX+"/list-by/{userId}")
    List<OrderDto> listByUserId(@PathVariable("userId")Long userId) throws InterruptedException;

}
