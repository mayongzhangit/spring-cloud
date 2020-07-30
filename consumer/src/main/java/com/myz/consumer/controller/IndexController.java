package com.myz.consumer.controller;

import com.myz.consumer.controller.vo.IndexVo;
import com.myz.consumer.feign.OrderFeignApi;
import com.myz.consumer.feign.UserFeignApi;
import com.myz.order.dto.OrderDto;
import com.myz.user.api.UserApi;
import com.myz.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 16:38
 * @email 2641007740@qq.com
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserFeignApi userFeignApi;

    @Autowired
    private OrderFeignApi orderFeignApi;

    @GetMapping("/index")
    public IndexVo index(){
        Long userId = 1l;
        IndexVo indexVo = new IndexVo();

        UserDto userDto = userFeignApi.getById(userId);
        indexVo.setUserDto(userDto);

        List<OrderDto> orderDtos = orderFeignApi.listByUserId(userId);
        indexVo.setOrderDtos(orderDtos);
        return indexVo;
    }
}
