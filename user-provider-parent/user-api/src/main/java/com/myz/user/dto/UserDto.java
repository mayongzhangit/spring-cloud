package com.myz.user.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/28 20:20
 * @email 2641007740@qq.com
 */
@Data
@ToString
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private Date birthday;
}
