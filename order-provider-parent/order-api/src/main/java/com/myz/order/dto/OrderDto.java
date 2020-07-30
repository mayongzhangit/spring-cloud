package com.myz.order.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/29 18:31
 * @email 2641007740@qq.com
 */
@Data
@ToString
public class OrderDto implements Serializable {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Double price;
}
