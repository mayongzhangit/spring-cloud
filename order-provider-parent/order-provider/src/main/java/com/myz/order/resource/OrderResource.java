package com.myz.order.resource;

import com.myz.order.api.OrderApi;
import com.myz.order.controller.vo.UserOrderVo;
import com.myz.order.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 18:38
 * @email 2641007740@qq.com
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderResource implements OrderApi {
    Random random = new Random();

    @PostMapping("/generate/{goodsId}}")
    public Long generate(@PathVariable Long goodsId){
        return 1L;
    }


    @GetMapping("/list-by/{userId}")
    public List<OrderDto> listByUserId(@PathVariable("userId")Long userId) throws InterruptedException {
        int sleepTime = random.nextInt(5000);

        Thread.sleep(sleepTime);

        log.info("order#listByUserId entry");
        List<OrderDto> dataLs = new ArrayList<OrderDto>();
        for (int i=0;i<10;i++){
            OrderDto orderDto = new OrderDto();
            orderDto.setId((long) i+userId);
            orderDto.setGoodsId((long) i+userId);
            orderDto.setUserId((long) i+userId);
            orderDto.setPrice((double) i+userId);
            orderDto.setSleepTime(sleepTime);
            dataLs.add(orderDto);
        }
        log.info("order#listByUserId exit");
        return dataLs;
    }
}
