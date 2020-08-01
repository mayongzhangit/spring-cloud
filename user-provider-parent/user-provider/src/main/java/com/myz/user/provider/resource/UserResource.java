package com.myz.user.provider.resource;

import com.myz.user.api.UserApi;
import com.myz.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/28 20:59
 * @email 2641007740@qq.com
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserResource implements UserApi {

    Random random = new Random();
    /**
     *
     * @param userDto
     * @return
     */
    @PostMapping("/save")
    public Long saveUser(@RequestBody UserDto userDto) {
        log.info("userDto={}",userDto);
        return userDto.getId();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public UserDto getById(@PathVariable Long id) throws InterruptedException {
        log.info("user#getById entry");
        int sleepTime = random.nextInt(5000);

        Thread.sleep(sleepTime);

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName("name"+id);
        userDto.setBirthday(new Date());
        log.info("user#getById exit");
        return userDto;
    }
}
