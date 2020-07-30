package com.myz.order.resource;

import com.myz.order.api.OrderApi;
import com.myz.order.controller.vo.UserOrderVo;
import com.myz.order.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 18:38
 * @email 2641007740@qq.com
 */
@RestController
@RequestMapping("/order")
public class OrderController implements OrderApi {

    @PostMapping("/generate/{goodsId}}")
    public Long generate(@PathVariable Long goodsId){
        return 1L;
    }

    @GetMapping("/list-by/{userId}")
    public List<OrderDto> listByUserId(@PathVariable("userId")Long userId){
        List<OrderDto> dataLs = new ArrayList<OrderDto>();
        for (int i=0;i<10;i++){
            OrderDto orderDto = new OrderDto();
            orderDto.setId((long) i+userId);
            orderDto.setGoodsId((long) i+userId);
            orderDto.setUserId((long) i+userId);
            orderDto.setPrice((double) i+userId);
            dataLs.add(orderDto);
        }
        return dataLs;
    }
}
