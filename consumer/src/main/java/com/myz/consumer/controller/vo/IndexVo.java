package com.myz.consumer.controller.vo;

import com.myz.order.dto.OrderDto;
import com.myz.user.dto.UserDto;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 17:02
 * @email 2641007740@qq.com
 */
@Data
@ToString
public class IndexVo implements Serializable {

    private UserDto userDto;

    private List<OrderDto> orderDtos;
}
